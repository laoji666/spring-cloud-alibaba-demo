package com.laoji.business.feign.fallback;

import com.laoji.business.dto.IconParam;
import com.laoji.business.dto.PasswordParam;
import com.laoji.business.dto.ProfileParam;
import com.laoji.business.feign.ProfileFeign;
import com.laoji.commons.dto.ResponseResult;
import com.laoji.commons.utils.MapperUtils;
import org.springframework.stereotype.Component;

/**
*  个人信息熔断触发
*  @author: laoji
*  @date:2020/4/9 11:52
*/
@Component
public class ProfileFeignFallBack implements ProfileFeign {
    public static final String BREAKING_MESSAGE="您的网络有问题，请检查后重试!";

    @Override
    public String info(String username) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.BREAKING,BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String update(ProfileParam profileParam) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.BREAKING,BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String updateIcon(IconParam iconParam) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.BREAKING,BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String updatePassword(PasswordParam passwordParam) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.BREAKING,BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
