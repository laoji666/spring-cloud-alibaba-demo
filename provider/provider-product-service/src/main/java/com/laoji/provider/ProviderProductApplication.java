package com.laoji.provider;

import com.laoji.configuration.DubboSentinelConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
*  商品服务提供者
*  @author: laoji
*  @date:2020/4/13 16:29
*/
@SpringBootApplication(scanBasePackageClasses = {ProviderProductApplication.class, DubboSentinelConfiguration.class})
@MapperScan(basePackages = "com.laoji.provider.mapper")
public class ProviderProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderProductApplication.class,args);
    }
}
