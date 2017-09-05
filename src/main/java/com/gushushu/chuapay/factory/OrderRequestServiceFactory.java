package com.gushushu.chuapay.factory;

import com.gushushu.chuapay.common.ResponseBody;
import com.gushushu.chuapay.model.OrderRequest;
import com.gushushu.chuapay.service.OrderRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderRequestServiceFactory {


    public static final String NEW_BANK = "NEW_BANK";//网上银行类型

    @Autowired
    private OrderRequestService netBankServiceImpl;

    /**
     * 创建一个订单请求
     * @param orderRequest
     * @return
     */
    public  ResponseBody create(OrderRequest orderRequest){

        ResponseBody rb = new ResponseBody();

        //TODO 验证订单Sign
        OrderRequestService orderRequestService = null;
        if(orderRequest.equals(orderRequest.getType())){
            orderRequestService = netBankServiceImpl;
        }else{
            rb.error("orderRequest type invalid");
        }


        return rb;
    }




}
