package com.gushushu.chuapay.service;

import com.gushushu.chuapay.common.ResponseBody;
import com.gushushu.chuapay.model.OrderRequest;

public interface OrderRequestService {

    public ResponseBody create(OrderRequest orderRequest);



}
