package com.gushushu.pay.common;

import java.util.Map;

public class SignEncode {

    public static String encode(Map<String,Object> paramMap, String signKey){
        String signStr = MapUtils.getSignStrByTreeMap(paramMap, false);
        signStr += "&key=" + signKey;
        String signValue = MD5.encryption(signStr).toUpperCase();
        return signValue;
    }

    public static String encode(Map<String,Object> paramMap){
        String signStr = MapUtils.getSignStrByTreeMap(paramMap, false);
        String signValue = MD5.encryption(signStr).toUpperCase();
        return signValue;
    }






}
