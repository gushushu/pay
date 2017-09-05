package com.gushushu.chuapay.controller;

import com.gushushu.chuapay.common.ResponseBody;
import com.gushushu.chuapay.factory.OrderRequestServiceFactory;
import com.gushushu.chuapay.model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单请求控制器
 */
@RestController
@RequestMapping("/orderRequest")
public class OrderRequestController {

    @Autowired
    private OrderRequestServiceFactory orderRequestServiceFactory;

    /**
     * 创建订单
     * @param orderRequest
     * orderId 必填
     * payAmt 必填
     * sign 必填
     * custId 必填
     * backUrl 必填
     * frontUrl 选填
     * @return
     */
    @PostMapping
    public ResponseBody create(OrderRequest orderRequest){
        return orderRequestServiceFactory.create(orderRequest);
    }



}
