package com.laoji.message.consumer;

import com.laoji.commons.utils.MapperUtils;
import com.laoji.provider.api.UmsAdminLoginLogService;
import com.laoji.provider.domain.UmsAdminLoginLog;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
@Service
public class AdminLoginLogReceive {
    @Reference
    private UmsAdminLoginLogService umsAdminLoginLogService;
    @StreamListener("admin-login-log-topic")
    public void receiveAdminLoginLog(String umsAdminLoginLogJson) throws Exception {
        System.out.println(umsAdminLoginLogJson);
        UmsAdminLoginLog umsAdminLoginLog = MapperUtils.json2pojo(umsAdminLoginLogJson, UmsAdminLoginLog.class);
        umsAdminLoginLogService.insert(umsAdminLoginLog);
    }
}
