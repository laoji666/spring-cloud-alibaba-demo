package com.laoji.cloud.api;

import com.laoji.cloud.dto.UmsAdminLoginLogDTO;

/**
*
*  @author: laoji
*  @date:2020/4/11 19:26
*/
public interface MessageService {

    /**
     * 发送登录日志
     * @param umsAdminLoginLogDTO
     * @return
     */
    boolean sendAdminLoginLog(UmsAdminLoginLogDTO umsAdminLoginLogDTO);

}
