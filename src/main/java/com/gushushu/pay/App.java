package com.gushushu.pay;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class App {



    public static void main(String[] argv) throws IOException {
        InputStream stream = App.class.getResourceAsStream("/log4j.properties");
       /* Properties properties = new Properties();
        properties.load(stream);*/
        PropertyConfigurator.configure(stream);

        SpringApplication.run(App.class,argv);
    }



}
