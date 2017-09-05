package com.gushushu.chuapay.common;

import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CopyMap<T> {

    public static TreeMap copy(Map map){
        Iterator<String> iterator = map.keySet().iterator();
        TreeMap ret = new TreeMap();
        while (iterator.hasNext()){
            String name = iterator.next();
            ret.put(name,map.get(name));
        }
        
        return ret;
    }
    

}
