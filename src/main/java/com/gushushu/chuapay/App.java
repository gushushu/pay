package com.gushushu.chuapay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;

@SpringBootApplication
public class App {


    public static void main(String[] argv){
        SpringApplication.run(App.class,argv);
    }


    @Bean
    public Map payConfig() throws IOException {
        InputStream is = App.class.getResourceAsStream("/payConfig.properties");
        Properties properties = new Properties();
        properties.load(is);
        is.close();
        //return PropertiesLoaderUtils.loadAllProperties("/payConfig.properties");
        return properties;
    }


}
