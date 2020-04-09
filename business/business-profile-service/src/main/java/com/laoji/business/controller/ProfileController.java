package com.laoji.business.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.laoji.business.controller.fallback.ProfileControllerFallback;
import com.laoji.business.dto.IconParam;
import com.laoji.business.dto.PasswordParam;
import com.laoji.business.dto.ProfileParam;
import com.laoji.business.dto.UmsAdminDto;
import com.laoji.commons.dto.ResponseResult;
import com.laoji.provider.api.UmsAdminService;
import com.laoji.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 获取个人信息
     *
     * @param username 用户名
     * @return {@link ResponseResult}
     */
    @GetMapping(value = "info/{username}")
    @SentinelResource(value = "info",fallback = "info",fallbackClass = ProfileControllerFallback.class)
    public ResponseResult<UmsAdminDto> info(@PathVariable String username) {
        UmsAdmin umsAdmin = umsAdminService.get(username);
        UmsAdminDto umsAdminDto=new UmsAdminDto();
        BeanUtils.copyProperties(umsAdmin,umsAdminDto);
        return new ResponseResult<UmsAdminDto>(ResponseResult.OK, "获取个人信息", umsAdminDto);
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

    @PostMapping(value = "updatePassword")
    public ResponseResult<Void> updatePassword(@RequestBody PasswordParam passwordParam){
        UmsAdmin umsAdmin = umsAdminService.get(passwordParam.getUserName());

        if(passwordEncoder.matches(passwordParam.getOldPassword(),umsAdmin.getPassword())){
            //验证通过
            int result = umsAdminService.updatePassword(passwordParam.getUserName(), passwordParam.getNewPassword());
            if(result>0){
                return new ResponseResult<Void>(ResponseResult.OK,"修改成功！");
            }

        }else{
            //验证不通过
            return new ResponseResult<Void>(ResponseResult.FAIL,"原密码错误！");
        }

        return new ResponseResult<Void>(ResponseResult.FAIL,"修改密码失败！");
    }
}
