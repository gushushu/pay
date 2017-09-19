package com.gushushu.pay.service;

import com.gushushu.pay.common.ResponseBody;
import com.gushushu.pay.model.Orders;

/**
 * 前台回调服务
 */
public interface FrontCallService {

    /**
     * 获取回调地址
     * @param orders
     * orderRequestId 必填
     * @return
     */
    ResponseBody findFrontUrl(Orders orders);


}
