package com.gushushu.pay.tfb;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import com.gushushu.pay.service.OrderService;
import com.gushushu.pay.tfb.util.MD5Utils;
import com.gushushu.pay.tfb.util.RSAUtils;
import com.gushushu.pay.tfb.util.RequestUtils;

import io.netty.util.internal.StringUtil;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.gushushu.pay.App;
import com.gushushu.pay.common.ResponseBody;
import com.gushushu.pay.config.AppKey;
import com.gushushu.pay.model.Orders;

@Component
public class TianOrderServiceImpl implements OrderService,AppKey {


	private Properties bankConfig;

	public TianOrderServiceImpl() throws IOException {
		InputStream is = App.class.getResourceAsStream("/tianFuBaoPay.properties");
		Properties properties = new Properties();
		properties.load(is);
		is.close();
		bankConfig = properties;
	}

	public ResponseBody create(Orders orderRequest) {
		// TODO Auto-generated method stub
		ResponseBody RB = new ResponseBody();
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		paramsMap.put("spid", TFBConfig.spid); 					//商户号
		paramsMap.put("sp_userid", orderRequest.getOrderId());	//用户号
		paramsMap.put("spbillno", orderRequest.getOrderId());	//订单号
		paramsMap.put("money", orderRequest.getPayAmt().toString());	//交易金额
		paramsMap.put("cur_type", TFBConfig.cur_type);			//金额单位

		paramsMap.put("return_url", orderRequest.getFrontUrl());		//页面回调地址
		paramsMap.put("notify_url", orderRequest.getBackUrl());		//后台回调地址
		paramsMap.put("errpage_url", TFBConfig.errpage_url);	//错误页面地址
		paramsMap.put("memo", TFBConfig.prd_name);				//商品名称
		paramsMap.put("expire_time", TFBConfig.expire_time);	//过期时间

		paramsMap.put("attach", TFBConfig.tfb_pay);				//自定义数据，提交什么，返回什么
		paramsMap.put("card_type", TFBConfig.CARD_TYPE);		//银行卡类型。 1：借记  2：贷记卡
		paramsMap.put("bank_segment", bankConfig.getProperty(orderRequest.getBankType()));	//银行代码
		paramsMap.put("user_type", TFBConfig.USER_TYPE);		//用户类型。 1：个人  2：企业

		paramsMap.put("channel", TFBConfig.channel);			//商户的用户使用的终端类型。1 – PC端，2 – 手机端
		paramsMap.put("encode_type", TFBConfig.encode_type);	//签名的方法。目前支持: MD5，RSA

		//拼接签名原串
		String paramSrc = RequestUtils.getParamSrc(paramsMap);

		//生成签名
		String sign = MD5Utils.sign(paramSrc);

		//rsa加密原串
		String encryptSrc = paramSrc + "&sign=" + sign;//加密原串

		//rsa密串
		String cipherData = RSAUtils.encrypt(encryptSrc);

		String url = null;

		try {
			url = TFBConfig.cardPayApplyApi.concat("?cipher_data=").concat(URLEncoder.encode(cipherData, TFBConfig.serverEncodeType));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return RB.success(url);
	}

	public ResponseBody update(Map resp) {
		// TODO Auto-generated method stub
		ResponseBody RB = new ResponseBody();

		//获取返回的状态码
		String returnCode = resp.get("retcode").toString();

		//获取结果
		String retMsg = "";
		try {
			retMsg = new String(resp.get("retmsg").toString().getBytes(),"gbk");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if(!StringUtils.isEmpty(returnCode) && TFBConfig.SUCCESS_CODE.equals(returnCode)){

			Orders order = new Orders();

			String sigStr = resp.get("cipher_data").toString();

			//数据解密
			String responseData = RSAUtils.decrypt(sigStr);

			//封装数据
			HashMap<String, String> map = RequestUtils.parseString(responseData);

			//rsa验签
			try {
				if (RSAUtils.verify(map.get("source"), map.get("sign"))) {
					//获取商户订单号
					order.setOrderId(map.get("spbillno").toString());

					String tranResult = map.get("result").toString();

					if(!StringUtils.isEmpty(tranResult) && TFBConfig.TRAN_SUCCESS_CODE.equals(tranResult)){
						order.setRetMessage("pay success");
						order.setStatus(ORDER_STATUS_PAY_SUCCESS);
					}else{
						order.setRetMessage("pay failed");
						order.setStatus(ORDER_STATUS_PAY_FAIL);
					}

					order.setRetStr(TFBConfig.RET_STR);
					RB.success(order);
				} else {
					RB.error().setMessage("验签失败");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			RB.error().setMessage(retMsg);
		}


		return RB;
	}

}
