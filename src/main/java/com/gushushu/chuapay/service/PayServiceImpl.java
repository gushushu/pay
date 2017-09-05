/*
package com.gushushu.chuapay.service;

import com.gushushu.chuapay.common.CopyMap;
import com.gushushu.chuapay.common.ResponseBody;
import com.gushushu.chuapay.common.SignEncode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.SortedMap;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private Map payConfig;

    */
/**
     * 银行在线支付
     * @param orderNo 订单号
     * @param payAmt 支付金额
     * @param bankCode 银行类型
     * @return
     *//*

    public ResponseBody onlineBank(String orderNo, Long payAmt, String bankCode) {
        SortedMap sortPayConfig = (SortedMap) CopyMap.copy(payConfig);
        sortPayConfig.put("payAmt",payAmt);
        sortPayConfig.put("tranType","0601");
        sortPayConfig.put("custOrderNo",orderNo);
        sortPayConfig.put("bankCode",bankCode);





    //    String action = "cashier/pay.ac";// 下单或支付;
        String key = (String) sortPayConfig.get("key");
        sortPayConfig.remove("key");
        String sign = SignEncode.encode(sortPayConfig, key);

        sortPayConfig.put("sign", sign);

     */
/*   String resStr = HttpUtil.httpPost(url + action, payConfig);
        System.out.println(resStr);

        return JUtil.toMap(resStr);
*//*



        return new ResponseBody().success(sortPayConfig);
    }
}
*/
