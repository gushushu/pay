package com.gushushu.chuapay.common;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringRandom {
	
	final public static List<String> filterList = new ArrayList<String>();
	static{
		filterList.add("0");
		filterList.add("O");
	}

	//生成随机数字和字母,
	public String getStringRandom(int length) {
		
		String val = "";
		Random random = new Random();
		
		String current = "";
		
		//参数length，表示生成几位随机数
		for(int i = 0; i < length; i++) {
			
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			//输出字母还是数字
			if( "char".equalsIgnoreCase(charOrNum) ) {
				//输出是大写字母还是小写字母
				int temp = 65 ;
				current = ((char)(random.nextInt(26) + temp)) + "";
			} else if( "num".equalsIgnoreCase(charOrNum) ) {
				current = String.valueOf(random.nextInt(10));
			}
			if(filterList.indexOf(current) != -1){
				i--;
			}else{
				val += current;
			}
		}
		return val;
	}
	
	public static void  main(String[] args) {
		StringRandom test = new StringRandom();
		//测试
		for (int i = 0; i < 20; i++) {
			
			System.out.println(test.getStringRandom(10));
		}
	}
}