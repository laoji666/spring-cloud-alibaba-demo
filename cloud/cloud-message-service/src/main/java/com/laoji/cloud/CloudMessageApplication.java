package com.laoji.cloud;

import com.laoji.cloud.message.MessageSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
*
*  @author: laoji
*  @date:2020/4/16 11:05
*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({MessageSource.class})
public class CloudMessageApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudMessageApplication.class, args);
    }
}
