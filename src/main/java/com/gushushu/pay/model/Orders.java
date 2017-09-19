package com.gushushu.pay.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class Orders {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid")
    private String orderRequestId;//订单请求Id
    private String orderId;//订单号
    private Long payAmt;//支付金额
    private String bankType;//支付银行
    private String frontUrl;//前台回调地址
    private String backUrl;//后台回调地址
    private String sign;//签名
    private String custId;//商户id
    private String type;//支付类型 （NET_BANK）
    private String status;//支付状态
    private Date createDate;//创建时间
    private Date backDate;//返回时间
    @Column(length = 4096)
    private String clientInfo;//客户端信息
    private String params;//订单请求的参数
    private String retMessage;//返回请求订单的平台消息
    private String retStr;//返回给订单结果通知的字符串

    public String getRetStr() {
        return retStr;
    }

    public void setRetStr(String retStr) {
        this.retStr = retStr;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Date getBackDate() {
        return backDate;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getOrderRequestId() {
        return orderRequestId;
    }

    public void setOrderRequestId(String orderRequestId) {
        this.orderRequestId = orderRequestId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(Long payAmt) {
        this.payAmt = payAmt;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Orders{" +
                "orderRequestId='" + orderRequestId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", payAmt=" + payAmt +
                ", bankType='" + bankType + '\'' +
                ", frontUrl='" + frontUrl + '\'' +
                ", backUrl='" + backUrl + '\'' +
                ", sign='" + sign + '\'' +
                ", custId='" + custId + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                ", backDate=" + backDate +
                ", clientInfo='" + clientInfo + '\'' +
                ", params='" + params + '\'' +
                ", retMessage='" + retMessage + '\'' +
                ", retStr='" + retStr + '\'' +
                '}';
    }
}
