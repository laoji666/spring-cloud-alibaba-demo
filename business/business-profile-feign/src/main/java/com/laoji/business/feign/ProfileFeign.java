package com.laoji.business.feign;

import com.laoji.business.dto.IconParam;
import com.laoji.business.dto.PasswordParam;
import com.laoji.business.dto.ProfileParam;
import com.laoji.business.feign.fallback.ProfileFeignFallBack;
import com.laoji.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 个人信息管理
 * <p>
 * Description:
 * </p>
 *
 *  @author: laoji
 *  @date:2020/4/6 18:01
 */
@FeignClient(value = "business-profile", path = "profile", configuration = FeignRequestConfiguration.class,fallback = ProfileFeignFallBack.class)
public interface ProfileFeign {
    /**
     * 根据用户名获取用户个人信息
     * @param username
     * @return
     */
    @GetMapping(value = "info/{username}")
    String info(@PathVariable String username);

    /**
     * 根据参数更新用户信息
     * @param profileParam
     * @return
     */
    @PostMapping(value = "update")
    String update(@RequestBody ProfileParam profileParam);

    /**
     * 修改头像
     * @param iconParam
     * @return
     */
    @PostMapping(value = "updateIcon")
    String updateIcon(@RequestBody IconParam iconParam);

    /**
     * 修改密码
     * @param passwordParam
     * @return
     */
    @PostMapping(value = "updatePassword")
    String updatePassword(@RequestBody PasswordParam passwordParam);
}
