package com.gushushu.pay.service;

import com.gushushu.pay.App;
import com.gushushu.pay.common.CopyMap;
import com.gushushu.pay.common.MapUtils;
import com.gushushu.pay.common.ResponseBody;
import com.gushushu.pay.common.SignEncode;
import com.gushushu.pay.config.AppKey;
import com.gushushu.pay.model.Orders;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;

public class ChuanHuaOrderService implements OrderService,AppKey {

    /**
     * 支付配置
     */
    private Properties payConfig;

    public ChuanHuaOrderService() throws IOException {
        InputStream is = App.class.getResourceAsStream("/chuanHuaPay.properties");
        Properties properties = new Properties();
        properties.load(is);
        is.close();
        payConfig = properties;
    }

    public ResponseBody create(Orders orderRequest) {

        SortedMap sortPayConfig = (SortedMap) CopyMap.copy(payConfig);
        sortPayConfig.put("payAmt",orderRequest.getPayAmt());
        sortPayConfig.put("tranType","0601");
        sortPayConfig.put("custOrderNo",orderRequest.getOrderId());
        sortPayConfig.put("bankCode",orderRequest.getBankType());
        sortPayConfig.put("backUrl",orderRequest.getBackUrl());
        sortPayConfig.put("frontUrl",orderRequest.getFrontUrl());

        String key = (String) sortPayConfig.get("key");
        sortPayConfig.remove("key");
        String sign = SignEncode.encode(sortPayConfig, key);

        sortPayConfig.put("sign", sign);
        String payUri = payConfig.get("payAddress") +"?"+ MapUtils.toStringParams(sortPayConfig);
        return new ResponseBody().success(payUri);

    }

    public ResponseBody update(Map resp) {

        ResponseBody rb = new ResponseBody();


        String sign = (String) resp.get("sign");
        String key = (String)payConfig.get("key");
        String ordStatus = (String)resp.get("ordStatus");//订单状态
        String orderId = (String) resp.get("prdOrdNo");//订单号
       // String msg = (String) resp.get("msg");//订单描述消息
      //  String msg = "pay failed";//订单描述消息
        resp.remove("sign");

        String newSign = SignEncode.encode(resp,key);

        if(sign != null && sign.toUpperCase().equals(newSign)){//判断是否是合法请求
            Orders orders = new Orders();
            orders.setOrderRequestId(orderId);
            String status = ORDER_STATUS_WAIT_PAY;

            if("01".equals(ordStatus)){//判断订单是否成功
                status  = ORDER_STATUS_PAY_SUCCESS;
                orders.setRetStr("SC000000");
            }else if("02".equals(ordStatus)){//判断订单是否失败
                status  = ORDER_STATUS_PAY_SUCCESS;
            }

            orders.setStatus(status);
           // orders.setRetMessage(msg);

            rb.success(orders);
        }else{
            rb.error("invalid sign.");
        }
        return rb;
    }
}
