package com.laoji.provider.api;

import com.laoji.provider.domain.UmsAdmin;

/**
*  系统用户service
*  @author: laoji
*  @date:2020/4/5 11:13
*/
public interface UmsAdminService {


    /**
     * 插入数据
     * @param umsAdmin {@link UmsAdmin}
     * @return 大于0表示插入成功
     */
    int insert(UmsAdmin umsAdmin);

    /**
     * 根据用户名查询数据
     * @param username 用户名 {@link UmsAdmin}
     * @return {@link UmsAdmin}
     */
    UmsAdmin get(String username);

    /**
     * 根据信息查询数据
     * @param umsAdmin {@link UmsAdmin}
     * @return {@link UmsAdmin}
     */
    UmsAdmin get(UmsAdmin umsAdmin);

    int update(UmsAdmin umsAdmin);

    int updateIcon(String userName,String path);

    int updatePassword(String userName, String newPassword);
}
