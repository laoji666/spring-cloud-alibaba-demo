package com.laoji.provider.mapper;

import com.laoji.provider.domain.UmsAdmin;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

/**
*
*  @author: laoji
*  @date:2020/4/16 11:00
*/
public interface UmsAdminMapper extends MyMapper<UmsAdmin> {
    /**
     * 根据用户名获取用户权限字符
     * @param username
     * @return
     */
    List<String> getPermissionValueByUserName(String username);

    /**
     * 根据管理员id获取角色id列表
     * @param id
     * @return
     */
    List<Integer> getRoleByAdminId(Integer id);
}
