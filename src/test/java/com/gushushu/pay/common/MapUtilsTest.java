package com.gushushu.pay.common;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapUtilsTest {
    @Test
    public void toStringParams() throws Exception {
        Map map = new HashMap();
        /*map.put("name","hello");
        map.put("namesex","hello");
        map.put("namegg","");*/
        /*map.put("orderId", "1504850416878815");
        map.put("status", "PAY_SUCCESS");
        map.put("retMessage", "");
        map.put("custKey", "key");*/

        map.put("orderId","1504850416878815");
        map.put("status","PAY_SUCCESS");
        map.put("retMessage","");
        map.put("custKey","key");

       //String dist = MapUtils.toStringParams(map);
       String dist = SignEncode.encode(map);
        System.out.println(dist);

    }

}