package com.laoji.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
*
*  @author: laoji
*  @date:2020/4/10 21:36
*/
@SpringBootApplication
@MapperScan(basePackages = "com.laoji.provider.mapper")
public class ProviderUmsAdminLoginLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderUmsAdminLoginLogApplication.class,args);
    }
}
