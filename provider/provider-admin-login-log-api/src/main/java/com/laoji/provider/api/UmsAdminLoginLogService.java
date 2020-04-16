package com.laoji.provider.api;

import com.laoji.provider.domain.UmsAdminLoginLog;

/**
*  系统管理员登录日志表api
*  @author: laoji
*  @date:2020/4/10 21:33
*/
public interface UmsAdminLoginLogService {

    /**
     * 插入数据
     * @param umsAdminLoginLog
     * @return
     */
    int insert(UmsAdminLoginLog umsAdminLoginLog);

}
