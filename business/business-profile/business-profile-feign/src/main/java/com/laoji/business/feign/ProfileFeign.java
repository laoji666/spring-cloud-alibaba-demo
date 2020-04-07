package com.laoji.business.feign;

import com.laoji.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 个人信息管理
 * <p>
 * Description:
 * </p>
 *
 *  @author: laoji
 *  @date:2020/4/6 18:01
 */
@FeignClient(value = "business-profile", path = "profile", configuration = FeignRequestConfiguration.class)
public interface ProfileFeign {
    @GetMapping(value = "info/{username}")
    String info(@PathVariable String username);
}
