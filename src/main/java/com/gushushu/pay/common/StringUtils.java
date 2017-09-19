package com.gushushu.pay.common;

import java.util.HashMap;
import java.util.Map;

public class StringUtils {

    public static Map<String,String> ToMap(String src,String split,String eq){
        String[] pros = src.split(split);
        Map m = new HashMap();

        for(int i=0;i<pros.length;i++){
            String pro = pros[i];
            String[] arg = pro.split(eq);
            String name = arg[0];
            String value = arg.length==1?"":arg[1];
            m.put(name,value);
        }

        return m;
    }


}
