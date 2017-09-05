package com.gushushu.chuapay.common;

import java.util.Map;

public class SignEncode {

    public static String encode(Map<String,Object> paramMap, String signKey){
        String signStr = MapUtils.getSignStrByTreeMap(paramMap, true);
        signStr += "&key=" + signKey;
        String signValue = MD5.encryption(signStr).toUpperCase();
//		String signValue = MD5Util.digest(signStr).toUpperCase();
        return signValue;
    }



}
