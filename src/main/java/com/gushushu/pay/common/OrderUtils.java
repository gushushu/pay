package com.gushushu.pay.common;

import com.gushushu.pay.model.Orders;

public class OrderUtils {


    public static String getCallParam(Orders orders){
        String orderId = orders.getOrderId();
        String status = orders.getStatus();
        String retMessage = orders.getRetMessage();

        return String.format("orderId=%s&status=%s&retMessage=%s",orderId,status,retMessage);
    }

}
