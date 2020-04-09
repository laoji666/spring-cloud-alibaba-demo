package com.laoji.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
*  SSO上传服务  云上传
*  @author: laoji
*  @date:2020/4/8 18:04
*/
@SpringBootApplication
@EnableDiscoveryClient
public class CloudUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudUploadApplication.class,args);
    }
}
