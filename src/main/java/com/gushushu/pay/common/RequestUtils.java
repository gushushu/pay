package com.gushushu.pay.common;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestUtils {



    public static Map getParams(HttpServletRequest request){

        Map map = new HashMap<String, Object>();

        Enumeration<String> em = request.getParameterNames();
        while (em.hasMoreElements()){
            String name = em.nextElement();
            Object value = request.getParameter(name);

            map.put(name,value);
        }
        return map;
    }

    public static Map getClientInfo(HttpServletRequest request){


        Enumeration enumeration = request.getHeaderNames();
        Map map = new HashMap();

        while(enumeration.hasMoreElements()){
            String name = (String) enumeration.nextElement();
            map.put(name,request.getHeader(name));
        }

        map.put("remoteAddr",request.getRemoteAddr());
        return map;
    }


}
