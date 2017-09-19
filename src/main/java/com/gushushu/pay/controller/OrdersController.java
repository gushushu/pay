package com.gushushu.pay.controller;

import com.gushushu.pay.common.RequestUtils;
import com.gushushu.pay.common.ResponseBody;
import com.gushushu.pay.factory.OrderServiceFactory;
import com.gushushu.pay.model.Orders;
import org.apache.catalina.util.RequestUtil;
import org.apache.http.HttpResponse;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

/**
 * 订单请求控制器
 */
@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrderServiceFactory orderServiceFactory;




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
    @RequestMapping("/create")
    public ResponseBody create(HttpServletRequest request,Orders orderRequest,HttpServletResponse response){
        Enumeration<String> enumParsms = request.getParameterNames();
        StringBuilder params = new StringBuilder();

        if(enumParsms.hasMoreElements()){
            String name = enumParsms.nextElement();
            String value = request.getParameter(name);
            params.append(name + "=" + value);

        }
        while (enumParsms.hasMoreElements()){
            String name = enumParsms.nextElement();
            String value = request.getParameter(name);
            params.append("&"+name + "=" + value);
        }


        Map clientInfo = RequestUtils.getClientInfo(request);
        orderRequest.setClientInfo(clientInfo.toString());
        orderRequest.setParams(params.toString());
        ResponseBody<String> rb = orderServiceFactory.create(orderRequest);
        if(rb.isSuccess()){
            response.setStatus(301);
            response.setHeader("location",rb.getData());
        }
        return rb;
    }

    @RequestMapping("/update/{orderRequestId}/{type}")
    public ResponseBody update(Orders orders,HttpServletRequest request){
        Map<String,Object> params = RequestUtils.getParams(request);
        return orderServiceFactory.update(params,orders);
    }





}
