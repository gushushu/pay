package com.gushushu.pay.common;

import com.gushushu.pay.model.Orders;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderUtilsTest {
    @Test
    public void getCallParam() throws Exception {

        Orders orders = new Orders();
        orders.setOrderId("1111");
        orders.setStatus("SUCCESS");
        orders.setRetMessage("pay success");
        String order = OrderUtils.getCallParam(orders);
        System.out.println(order);

    }

}