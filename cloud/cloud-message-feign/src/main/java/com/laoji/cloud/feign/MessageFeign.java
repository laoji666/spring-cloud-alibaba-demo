package com.laoji.cloud.feign;

import com.laoji.cloud.feign.fallback.MessageFeignFallback;
import com.laoji.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 消息生产者服务feign
 * <p>
 * Description:
 * </p>
 *  @author: laoji
 *  @date:2020/4/8 18:02
 */
@FeignClient(value = "cloud-message", path = "message", configuration = FeignRequestConfiguration.class,fallback = MessageFeignFallback.class)
public interface  MessageFeign {

}
