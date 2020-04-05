package com.laoji.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
*
*  @author: laoji
*  @date:2020/4/5 0:02
*/
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BusinessRegApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessRegApplication.class,args);
    }
}
