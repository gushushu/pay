/*
package com.gushushu.chuapay.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;


public class SendUtil {
	public static String SendHttpRequest(String url,
			Map<String, Object> paramMap, String method, String signKey)
			throws Exception {
		if (!"POST".equalsIgnoreCase(method)) {
			method = "GET";
		}
		
		String paramString = JUtil.toJsonString(paramMap);
		
		//打印日志的参数列表
		Map<String,Object> logParamMap = new HashMap<String,Object>(paramMap);
		logParamMap.remove("cardHandheld");
		logParamMap.remove("cardFront");
		logParamMap.remove("cardBack");
		System.out.println("请求参数列表(已忽略照片参数)params:" + logParamMap);
		paramString = new String(Base64.encodeBase64(paramString.getBytes()));
		String sign =  MD5Util.digest(paramString + signKey).toUpperCase();
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("sign", sign);
		params.put("params", paramString);
		String resStr = HttpUtil.httpRequest(url, params, method);
		System.out.println("返回" +resStr);
		Map<String,Object> resMap = JUtil.toMap(resStr);
		paramString = resMap.get("params").toString();
		String chlSign = resMap.get("sign").toString();
		sign =  MD5Util.digest(paramString + signKey).toUpperCase();
		if(!sign.equalsIgnoreCase(chlSign)){
			throw new Exception("验签失败...");
		}
		System.out.println("返回验签成功");
		resStr = new String(Base64.decodeBase64(paramString.getBytes()));
		return resStr;
	}
	
	public static String SendSimpleHttpRequest(String url,
			Map<String, Object> paramMap, String method, String signKey)
			throws Exception {
		if (!"POST".equalsIgnoreCase(method)) {
			method = "GET";
		}
		String signStr = MapUtils.getSignStrByTreeMap(paramMap, true);
		System.out.println("签名元数据:" + signStr);
		String sign =  MD5Util.digest(signStr + "&key=" +signKey).toUpperCase();
		System.out.println("签名结果:" + sign);
		paramMap.put("sign", sign);
		
		String resStr = HttpUtil.httpRequest(url, paramMap, method);
//		System.out.println("返回" +resStr);
//		Map<String,Object> resMap = JUtil.toMap(resStr);
		return resStr;
	}
}
*/
