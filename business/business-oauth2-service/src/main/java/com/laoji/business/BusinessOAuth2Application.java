package com.laoji.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
*
*  @author: laoji
*  @date:2020/4/16 11:04
*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BusinessOAuth2Application {
    public static void main(String[] args) {
        SpringApplication.run(BusinessOAuth2Application.class,args);
    }
}
