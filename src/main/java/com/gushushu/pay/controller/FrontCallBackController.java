package com.gushushu.pay.controller;

import com.gushushu.pay.common.ResponseBody;
import com.gushushu.pay.model.Orders;
import com.gushushu.pay.repository.OrderRepository;
import com.gushushu.pay.service.FrontCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 前台回调控制器
 */
@RestController
public class FrontCallBackController {

    @Autowired
    private FrontCallService frontCallService;


    @RequestMapping("/call/front/{orderRequestId}")
    public void frontCall(Orders orders, HttpServletResponse response) throws IOException {

        ResponseBody rb = frontCallService.findFrontUrl(orders);
        if(rb.isSuccess()){
            response.setStatus(301);
            response.setHeader("location",(String) rb.getData());
        }

    }



}
