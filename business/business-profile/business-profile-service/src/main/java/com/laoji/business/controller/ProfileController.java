package com.laoji.business.controller;

import com.laoji.commons.dto.ResponseResult;
import com.laoji.provider.api.UmsAdminService;
import com.laoji.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 个人信息管理
 * <p>
 * Description:
 * </p>
 *
 *  @author: laoji
 *  @date:2020/4/6 18:06
 */
@RestController
@RequestMapping(value = "profile")
public class ProfileController {
    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;
    /**
     * 获取个人信息
     *
     * @param username 用户名
     * @return {@link ResponseResult}
     */
    @GetMapping(value = "info/{username}")
    public ResponseResult<UmsAdmin> info(@PathVariable String username) {
        UmsAdmin umsAdmin = umsAdminService.get(username);
        return new ResponseResult<UmsAdmin>(ResponseResult.OK, "获取个人信息", umsAdmin);
    }
}
