/*
package com.gushushu.chuapay.common;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class StringUtil {
	*/
/**
	 * 查找mybatis的键值对
	 *//*

	public static void indexMyBatisCV(String c,String v){
		String[] sz_c = c.split(",");
		String [] sz_v = v.split(",");
		
		String idxStr = "OEM_STATE";
		for (int i = 0; i < sz_c.length; i++) {
			if(sz_c[i].trim().equals(idxStr)){
				System.out.println(sz_c[i] + " = " + sz_v[i]);
			}
		}
	}
	
	*/
/**
	 * 打印json请求参数到map赋值格式
	 * @param jsonStr
	 *//*

	public static void jsonPrintToMap(String jsonStr){
		Map<String,Object> pmap = new LinkedHashMap<String,Object>();
		pmap = JUtil.toMap(jsonStr);
//		System.out.println(s);
		System.out.println(pmap);
		Set<String> keySet = pmap.keySet();
		for (String key : keySet) {
//			paramMap.put("custId", "16012000001329");
			System.out.println("paramMap.put(\""+key+"\",\""+pmap.get(key)+"\");");
			
		}
	}
	
	*/
/**
	 * 打印map结果集字符串还原到map
	 * @param jsonStr
	 *//*

	public static void mapPrintToMap(String str){
		if(str.startsWith("{")){
			str = str.substring(1,str.length() -1);//去掉首位括号
		}
		
		Map<String,Object> pmap = new LinkedHashMap<String,Object>();
		String[] sz = str.split(", ");//不保证特殊情况下处理不正确的情况出现
		for (String one : sz) {
			String[] oneSz = one.split("=");
			if(oneSz == null || oneSz.length <= 0){
				continue;
			}
			if(oneSz.length == 1){
				pmap.put(oneSz[0], "");
			}else if(oneSz.length == 2){
				if("null".equalsIgnoreCase(oneSz[1])){
					pmap.put(oneSz[0], null);
				}else{
					pmap.put(oneSz[0], oneSz[1]);
				}
			}
		}
		
//		pmap = JUtil.toMap(str);
//		System.out.println(s);
		System.out.println(pmap);
		Set<String> keySet = pmap.keySet();
		for (String key : keySet) {
//			paramMap.put("custId", "16012000001329");
			Object v = pmap.get(key);
			if(v == null){
				System.out.println("paramMap.put(\""+key+"\",null);");
			}else{
				System.out.println("paramMap.put(\""+key+"\",\""+pmap.get(key)+"\");");
				
			}
			
		}
	}
	
	*/
/**
	 * 打印终端入库语句
	 * @param terSn 终端编号
	 * @param terZmk 终端主密钥
	 *//*

	public static String getTerSql(String terSn,String terType,String terCom){
		Map<String,Object> param = new LinkedHashMap<String, Object>();
		param.put("TERMINAL_ID", terSn);
		param.put("TERMINAL_SEQ", terSn);
		param.put("TERMINAL_NO", terSn);
		param.put("TERMINAL_TYPE", terType);
		param.put("TERMINAL_COM", terCom);
		param.put("TERMINAL_STATUS", "0");
		param.put("RATE_LIVELIHOOD", "0.49");
		param.put("RATE_GENERAL", "0.78");
		param.put("RATE_GENERAL_TOP", "0.78");
		param.put("RATE_GENERAL_MAXIMUN", "3500");
		param.put("RATE_ENTERTAIN", "1.25");
		param.put("RATE_ENTERTAIN_TOP", "1.25");
		param.put("RATE_ENTERTAIN_MAXIMUN", "8000");

		return getInsertSql("MPAMNG_TERMINAL_INF",param);
	}
	
	public static String getTerKeySql(String terSn,String terZmk){
		Map<String,Object> param = new LinkedHashMap<String, Object>();
		param.put("TERMINAL_ID", terSn);
		param.put("TERMINAL_LMK", terZmk);
		param.put("TERMINAL_LMK_CV", "00000000");
		param.put("TERMINAL_ZMK", terZmk);
		param.put("TERMINAL_ZMK_CV", "00000000");
		param.put("STATUS", "0");
		
		return getInsertSql("MPAMNG_TERMINAL_KEY_INF",param);
	}
	
	public static List<String> numGrowth(String prefix,Integer start,Integer end,Integer len){
		List<String> numList = new ArrayList<String>();
		Integer temp = start;
		String num = null;
		while(temp <= end){
			num = temp + "";
			if(len != null){
				if(num.length() > len){
					num = num.substring(len-num.length());
					
				}else{
					for (int i = 0; i < len - num.length(); i++) {
						num = "0" + num;
					}
				}
			}
			
			num = prefix + num;
			numList.add(num);
			temp ++;
		}
		return numList;
	}
	
	public static String getInsertSql(String tableName,Map<String,Object> param){
		StringBuffer sql = new StringBuffer("");
		sql.append("INSERT INTO ").append(tableName);
		Set<String>	keySet = param.keySet();
		StringBuffer keySql = new StringBuffer("(");
		StringBuffer valueSql = new StringBuffer("(");
		int i = 0;
		Object obj = null;
		for (String key : keySet) {
			if(i > 0){
				keySql.append(",");
				valueSql.append(",");
			}
			keySql.append(key);
			obj = param.get(key);
			if(obj == null){
				valueSql.append("null");
			}else if(obj.getClass().isAssignableFrom(Integer.class)){
				valueSql.append(obj);
			}else{
				valueSql.append("'" + obj.toString() + "'");
			}
			
			i++;
		}
		keySql.append(")");
		valueSql.append(")");
		sql.append(keySql);
		sql.append("VALUES");
		sql.append(valueSql);
		sql.append(";");
		return sql.toString();
	}
	
	public static void main(String[] args) throws Exception{

	}
	
	public static String toUnicode(String str){
		char[] ch=str.toCharArray();
		String ss=null;
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < ch.length; i++) {
			ss="\\u"+Integer.toHexString(ch[i]);
			sb.append(ss);
		}
		return sb.toString();
	}
	
}
*/
