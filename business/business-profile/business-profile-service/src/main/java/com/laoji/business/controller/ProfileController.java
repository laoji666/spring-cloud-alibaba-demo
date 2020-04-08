package com.laoji.business.controller;

import com.laoji.business.dto.IconParam;
import com.laoji.business.dto.ProfileParam;
import com.laoji.commons.dto.ResponseResult;
import com.laoji.provider.api.UmsAdminService;
import com.laoji.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping(value = "update")
    public ResponseResult<Void> update(@RequestBody ProfileParam profileParam){
        UmsAdmin umsAdmin=new UmsAdmin();
        BeanUtils.copyProperties(profileParam,umsAdmin);

        int result = umsAdminService.update(umsAdmin);
        if(result>0){
            return new ResponseResult<Void>(ResponseResult.OK, "修改成功");
        }
        else{
            return new ResponseResult<Void>(ResponseResult.FAIL, "修改失败");
        }
    }

    @PostMapping(value = "updateIcon")
    public ResponseResult<Void> updateIcon(@RequestBody IconParam iconParam){
        int result = umsAdminService.updateIcon(iconParam.getUsername(), iconParam.getPath());
        if(result>0){
            return new ResponseResult<Void>(ResponseResult.OK, "修改成功");
        }
        else {
            return new ResponseResult<Void>(ResponseResult.FAIL, "修改失败");
        }

    }
}
