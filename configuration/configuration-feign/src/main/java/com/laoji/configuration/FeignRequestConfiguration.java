package com.laoji.configuration;

import com.laoji.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Feign 全局配置
 * <p>
 * Description:
 * </p>
 *
 *  @author: laoji
 *  @date:2020/4/6 17:56
 */
@Configuration
public class FeignRequestConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
