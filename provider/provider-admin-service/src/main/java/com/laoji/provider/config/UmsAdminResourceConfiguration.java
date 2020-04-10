package com.laoji.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 资源配置服务器
 * <p>
 * Description:
 * </p>
 *
 * @author laoji
 * @version v1.0.0
 * @date 2019-07-26 04:27:07
 * @see
 */
@Configuration
public class UmsAdminResourceConfiguration {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}