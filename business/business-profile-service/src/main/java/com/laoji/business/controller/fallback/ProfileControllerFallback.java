package com.laoji.business.controller.fallback;

import com.laoji.business.dto.UmsAdminDto;
import com.laoji.business.feign.fallback.ProfileFeignFallBack;
import com.laoji.commons.dto.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*  Profile熔断配置
*  @author: laoji
*  @date:2020/4/9 23:58
*/
public class ProfileControllerFallback {
    private static final Logger logger= LoggerFactory.getLogger(ProfileControllerFallback.class);

    public static ResponseResult<UmsAdminDto> info(String username,Throwable ex){
        logger.error("fuck : "+ex.getClass().getTypeName());

        return new ResponseResult<UmsAdminDto>(ResponseResult.BREAKING, ProfileFeignFallBack.BREAKING_MESSAGE,null);
    }
}
