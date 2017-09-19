package com.gushushu.pay.service;

import com.gushushu.pay.common.ResponseBody;
import com.gushushu.pay.model.Orders;

import java.util.Map;

public interface OrderService {

    ResponseBody create(Orders orderRequest);

    ResponseBody update(Map resp);

}
