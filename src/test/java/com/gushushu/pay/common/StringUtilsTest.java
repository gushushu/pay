package com.gushushu.pay.common;

import org.junit.Test;

import java.util.Map;

public class StringUtilsTest {
    @Test
    public void toMap() throws Exception {

        Map m  = StringUtils.ToMap("name=123&value=abc&a&b=","&","=");
        System.out.println(m);

    }

}