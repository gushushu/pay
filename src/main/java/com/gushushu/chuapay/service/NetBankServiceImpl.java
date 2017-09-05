package com.gushushu.chuapay.service;

import com.gushushu.chuapay.common.CopyMap;
import com.gushushu.chuapay.common.ResponseBody;
import com.gushushu.chuapay.common.SignEncode;
import com.gushushu.chuapay.model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.SortedMap;

/**
 * 网上银行支付
 */
@Component("netBankServiceImpl")
public class NetBankServiceImpl implements OrderRequestService {

    @Autowired
    private Map payConfig;

    public ResponseBody create(OrderRequest orderRequest){


        SortedMap sortPayConfig = (SortedMap) CopyMap.copy(payConfig);
        sortPayConfig.put("payAmt",orderRequest.getPayAmt());
        sortPayConfig.put("tranType","0601");
        sortPayConfig.put("custOrderNo",orderRequest.getOrderId());
        sortPayConfig.put("bankCode",orderRequest.getBankType());


        String key = (String) sortPayConfig.get("key");
        sortPayConfig.remove("key");
        String sign = SignEncode.encode(sortPayConfig, key);

        sortPayConfig.put("sign", sign);


        return new ResponseBody().success(sortPayConfig);
    }



}
