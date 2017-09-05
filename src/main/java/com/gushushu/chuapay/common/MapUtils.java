package com.gushushu.chuapay.common;

import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.TreeMap;


public class MapUtils extends org.apache.commons.collections.MapUtils{
	
	/**
	 * 按参数名称排序获取key=value&key=value
	 * @param paramMap 参数map
	 * @param ignoreEmpty 是否忽略掉为空的参数
	 * @return
	 */
	public static String getSignStrByTreeMap(Map<String,? extends Object> param,boolean ignoreEmpty){
		Map<String,Object> paramMap = new TreeMap<String, Object>();
		paramMap.putAll(param);
		
		return getSignStrByMap(paramMap,ignoreEmpty);
		
	}

	/**
	 * key=value&key=value
	 * @param paramMap 参数map
	 * @param ignoreEmpty 是否忽略掉为空的参数
	 * @return
	 */
	public static String getSignStrByMap(Map<String,? extends Object> param,boolean ignoreEmpty){
		
		if(param == null  || param.isEmpty()){
			return "";
		}
		StringBuffer str = new StringBuffer("");
		int i = 0;
		Object obj = null;
		for (String key : param.keySet()) {
			obj = param.get(key);
			//忽略空值
			if(ignoreEmpty){
				if(obj == null || StringUtils.isEmpty(obj.toString())){
					continue;
				}
			}
			
			if(i > 0){
				str.append("&");
			}
			str.append(key).append("=").append(param.get(key));
			i++;
		}
		
		return str.toString();
		
	}
}
