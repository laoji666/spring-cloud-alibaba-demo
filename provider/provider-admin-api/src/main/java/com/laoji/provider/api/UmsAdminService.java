package com.laoji.provider.api;

import com.github.pagehelper.PageInfo;
import com.laoji.provider.domain.UmsAdmin;

import java.util.List;

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

    /**
     * 更新
     * @param umsAdmin
     * @return
     */
    int update(UmsAdmin umsAdmin);

    /**
     * 更新头像
     * @param userName 用户名
     * @param path 新头像地址
     * @return
     */
    int updateIcon(String userName,String path);

    /**
     * 更新密码
     * @param userName
     * @param newPassword
     * @return
     */
    int updatePassword(String userName, String newPassword);

    /**
     * 根据用户名查询权限字符列表
     * @param username
     * @return
     */
    List<String> getPermissionValueByUserName(String username);

    /**
     * 根据管理员id获取角色列表
     * @param adminId
     * @return
     */
    List<Integer> getRoleByAdminId(Integer adminId);

    /**
     * 根据分页数据获取管理员列表
     * @param pageSize
     * @param pageNum
     * @return
     */
    PageInfo<UmsAdmin> getAll(Integer pageSize, Integer pageNum);

    /**
     * 更新管理员角色
     * @param newList
     * @param adminId
     * @return
     */
    boolean updateRole(List<Integer> newList,Integer adminId);

}
