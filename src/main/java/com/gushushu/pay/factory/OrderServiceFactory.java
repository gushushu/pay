package com.gushushu.pay.factory;

import com.gushushu.pay.App;
import com.gushushu.pay.common.ResponseBody;
import com.gushushu.pay.common.SignEncode;
import com.gushushu.pay.common.StringUtils;
import com.gushushu.pay.config.AppKey;
import com.gushushu.pay.config.SysConfig;
import com.gushushu.pay.model.Cust;
import com.gushushu.pay.model.Orders;
import com.gushushu.pay.repository.CustRepository;
import com.gushushu.pay.repository.OrderRepository;
import com.gushushu.pay.service.OrderService;
import com.gushushu.pay.service.PayNotify;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Component
public class OrderServiceFactory implements AppKey{


    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private CustRepository custRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PayNotify payNotify;

    @Autowired
    private SysConfig sysConfig;





    private Map<String,OrderService> instanceMap = new HashMap();

    public OrderServiceFactory() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        InputStream is = App.class.getResourceAsStream("/orderConfig.properties");
        Properties properties = new Properties();
        properties.load(is);
        is.close();


        Enumeration enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()){
            String orderRequestType = (String) enumeration.nextElement();
            String className = (String) properties.get(orderRequestType);

            OrderService service = (OrderService) Class.forName(className).newInstance();

            instanceMap.put(orderRequestType,service);

        }





    }

    /**
     * 验证订单请求签名
     * @param orderRequest
     * @return
     */
    private ResponseBody signValid(Orders orderRequest){




        ResponseBody rb = new ResponseBody();
        String custId = orderRequest.getCustId();
        Cust cust = null;
        if(custId != null){
            cust = custRepository.findOne(custId);
        }
        if(cust == null){//是否存在商户
            rb.error("invalid orderRequest for custId.");
        }else{
            String custKey = cust.getCustKey();
            Map params = StringUtils.ToMap(orderRequest.getParams(),"&","=");
            params.remove(SIGN);
            params.put("custKey",custKey);
            String sign = SignEncode.encode(params);

            if(orderRequest.getSign() != null && sign.equals(orderRequest.getSign().toUpperCase())){//判断签名是否有效
                rb.success();
            }else{
                String error = "invalid orderRequest for sign.";
                rb.error(error);

                logger.info(error);
            }

        }

        return rb;
    }



    /**
     * 创建一个订单请求
     * @param orderRequest
     * @return
     */
    public  ResponseBody create(Orders orderRequest){



        ResponseBody rb = signValid(orderRequest);

        if(rb.isSuccess()){//判断订单是否合法
            OrderService orderRequestService = instanceMap.get(orderRequest.getType());

            if(orderRequestService == null){//验证是否存在请求类型
                rb.error("invalid orderRequest type.");
            }else{

                orderRequest.setCreateDate(new Date());
                orderRequest.setStatus(ORDER_STATUS_WAIT_PAY);
                orderRepository.save(orderRequest);
                //设置回掉地址
                orderRequest.setBackUrl(sysConfig.getBackUrlPrefix()+"/order/update/"+orderRequest.getOrderRequestId()+"/"+orderRequest.getType());
                orderRequest.setFrontUrl(sysConfig.getBackUrlPrefix()+"/call/front/"+orderRequest.getOrderRequestId());
                rb = orderRequestService.create(orderRequest);
            }

        }

        return rb;
    }


    /**
     * 更新订单
     * @param map 依据参数
     * @param order 订单
     * @return
     */
    public ResponseBody update(Map map,Orders order){
        logger.info("---------update order");
        logger.info("params Map\t:"+map.toString());
        logger.info("params order\t:"+order.toString());

        OrderService orderService = instanceMap.get(order.getType());
        ResponseBody<Orders> rb = new ResponseBody<Orders>();

        if(orderService == null){//是否是合法的订单类型
            String error = "invalid order type";
            rb.error(error);
            logger.warn(error);
        }else{
            logger.info("valid order type");
            rb = orderService.update(map);

            if(rb.isSuccess()){//判断是否是合法的请求
                logger.info("valid order");
                Orders orders = rb.getData();
                String orderRequestId = order.getOrderRequestId();
                Orders orderDB  = orderRepository.findOne(orderRequestId);

                orderDB.setStatus(orders.getStatus());
                orderDB.setBackDate(new Date());
                orderDB.setRetStr(orders.getRetStr());
                orderDB.setRetMessage(orders.getRetMessage());
                orderRepository.save(orderDB);
                String retStr = payNotify.notify(orderDB);
                logger.info("notify url return str:\t"+ retStr);
            }else {
                logger.info("invalid order");
            }


        }
                return rb;

    }



}
