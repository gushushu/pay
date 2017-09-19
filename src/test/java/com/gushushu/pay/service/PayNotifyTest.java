package com.gushushu.pay.service;

import com.gushushu.pay.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
/*@SpringBootTest(classes = App.class)
@RunWith(SpringJUnit4ClassRunner.class)*/
public class PayNotifyTest {

   /* @Autowired
    private PayNotify payNotify;*/

    @Test
    public void httpPost() throws Exception {

        PayNotify notify = new PayNotify();
        Map map = new HashMap();
        map.put("a","b");
        map.put("v","呵呵");
        String ret = notify.httpPost("http://reqtest.hsjrzb.com/",map);

        System.out.println(ret);


    }

}