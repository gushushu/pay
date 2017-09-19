package com.gushushu.pay.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sysConfig")
public class SysConfig {

    private String backUrlPrefix;//支付结果回调地址

    public String getBackUrlPrefix() {
        return backUrlPrefix;
    }

    public void setBackUrlPrefix(String backUrlPrefix) {
        this.backUrlPrefix = backUrlPrefix;
    }
}
