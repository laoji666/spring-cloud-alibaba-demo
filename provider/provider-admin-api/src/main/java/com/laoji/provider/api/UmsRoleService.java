package com.laoji.provider.api;

import com.laoji.provider.domain.UmsRole;

import java.util.List;

/**
*  角色服务接口
*  @author: laoji
*  @date:2020/4/16 11:04
*/
public interface UmsRoleService {

    /**
     * 获取所有角色
     * @return
     */
    List<UmsRole> getAll();

}
