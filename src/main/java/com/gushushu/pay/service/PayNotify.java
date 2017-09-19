package com.gushushu.pay.service;

import com.gushushu.pay.common.MapUtils;
import com.gushushu.pay.common.SignEncode;
import com.gushushu.pay.model.Cust;
import com.gushushu.pay.model.Orders;
import com.gushushu.pay.repository.CustRepository;
import com.gushushu.pay.repository.OrderRepository;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * 支付结果通知
 */
@Service
public class PayNotify {

    @Autowired
    private CustRepository CustRepository;

    Logger logger = Logger.getLogger(this.getClass());

    /**
     * 通知
     * @param orders
     */
    public String notify(Orders orders){
        logger.info("----------notify backUrl:");
        logger.info("orders\t"+orders.toString());
        String ret = "";

        String orderId = orders.getOrderId();
        String status = orders.getStatus();
        String custId  = orders.getCustId();
        String retMessage = orders.getRetMessage();

        Map params = new HashMap<String, String>();
        Cust cust = CustRepository.findOne(custId);
        params.put("orderId",orderId);
        params.put("status",status);
        params.put("retMessage",retMessage);


        if(cust != null) {//判断订单所属商户是否存在

            params.put("custKey",cust.getCustKey());
            String sign = SignEncode.encode(params);
            params.put("sign", sign);
            params.remove("custKey");
            ret = httpPost(orders.getBackUrl(),params);

            logger.info("notify url\t:"+orders.getBackUrl());
            logger.info("notify params\t:"+params);
            logger.info("notify result\t:"+ret);
        }else{
            logger.warn("custId not found");
        }

        return ret;
    }




    public String httpPost(String url,Map<String,String> data)  {

        StringBuilder sb = new StringBuilder();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Iterator<String> iterator = data.keySet().iterator();
        HttpResponse response = null;
        while(iterator.hasNext()){
            String name = iterator.next();
            String value = data.get(name);
            params.add(new BasicNameValuePair(name,value));
        }


            try {
            HttpPost httpPost = new HttpPost(url);
            HttpClient httpClient = HttpClients.createDefault();

            httpPost.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
            entity.setContentEncoding("utf-8");
            httpPost.setEntity(entity);

            response = httpClient.execute(httpPost);
            InputStream is = response.getEntity().getContent();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(isr);

            String line = bufferedReader.readLine();
            do {
                sb.append(line);
            }while ((line = bufferedReader.readLine())!=null);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return sb.toString();
    }


}
