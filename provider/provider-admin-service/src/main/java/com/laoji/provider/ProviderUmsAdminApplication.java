package com.laoji.provider;

import com.laoji.configuration.DubboSentinelConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
*
*  @author: laoji
*  @date:2020/4/4 23:54
*/
@SpringBootApplication(scanBasePackageClasses = {ProviderUmsAdminApplication.class, DubboSentinelConfiguration.class})
@MapperScan(basePackages = "com.laoji.provider.mapper")
public class ProviderUmsAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderUmsAdminApplication.class,args);
    }
}
