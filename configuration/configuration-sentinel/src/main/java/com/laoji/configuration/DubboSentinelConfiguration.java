package com.laoji.configuration;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Dubbo Sentinel AOP
 * <p>
 * Description: 仅 Dubbo 服务需要该配置，Spring Cloud Alibaba 无需加载该配置
 * </p>
*  @author: laoji
*  @date:2020/4/9 23:43
*/
@Configuration
public class DubboSentinelConfiguration {
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
