package com.laoji.provider.tests;

import com.laoji.provider.api.UmsAdminLoginLogService;
import com.laoji.provider.domain.UmsAdminLoginLog;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class UmsAdminLoginLogTests {

    @Reference
    private UmsAdminLoginLogService umsAdminLoginLogService;

    @Test
    public void insertTest(){
        UmsAdminLoginLog umsAdminLoginLog=new UmsAdminLoginLog();
        umsAdminLoginLog.setAdminId(1L);
        umsAdminLoginLog.setCreateTime(new Date());
        umsAdminLoginLog.setIp("1.0.0.0");
        umsAdminLoginLog.setAddress("1.0.0.0");
        umsAdminLoginLog.setUserAgent("1.0.0.0");
        umsAdminLoginLogService.insert(umsAdminLoginLog);
    }
}
