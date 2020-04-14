package com.laoji.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
*
*  @author: laoji
*  @date:2020/4/14 13:07
*/
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class BusinessSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessSystemApplication.class,args);
    }
}
