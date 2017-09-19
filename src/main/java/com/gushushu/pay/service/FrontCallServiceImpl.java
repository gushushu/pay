package com.gushushu.pay.service;

import com.gushushu.pay.common.OrderUtils;
import com.gushushu.pay.common.ResponseBody;
import com.gushushu.pay.model.Orders;
import com.gushushu.pay.repository.OrderRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FrontCallServiceImpl implements FrontCallService {


    @Autowired
    private OrderRepository orderRepository;

    Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

    public ResponseBody findFrontUrl(Orders orders) {
        logger.info("--------find front url--------");

        ResponseBody rb = new ResponseBody();

        if(orders.getOrderRequestId() == null){//是否有orderRequestId
            logger.info("orderRequestId not in params.");
        }else{
            Orders orderDB = orderRepository.findOne(orders.getOrderRequestId());

            if(orderDB != null){//订单是否存在
                String url = orderDB.getFrontUrl();
                if(url == null){
                    logger.info("frontUrl is not setting.");
                }else{
                    String params = OrderUtils.getCallParam(orderDB);
                    String realParams = url.indexOf("?") == -1 ? ("?" +params):params;
                    String realUrl = url+realParams;
                    rb.success(url+realParams);
                    logger.info("real url\t"+ realUrl);
                }
            }else{
                String error = "invalid orderRequestId.";
                logger.info(error);
                rb.error(error);
            }
        }

        return rb;
    }
}
