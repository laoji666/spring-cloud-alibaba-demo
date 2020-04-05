package com.laoji.provider.tests;
import java.util.Date;

import com.laoji.provider.api.UmsAdminService;
import com.laoji.provider.domain.UmsAdmin;
import com.laoji.provider.mapper.UmsAdminMapper;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class UmsAdminServiceTest {
    @Autowired
    UmsAdminMapper umsAdminMapper;

    @Reference(version = "1.0.0")
    UmsAdminService umsAdminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void find(){
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectAll();
        for (UmsAdmin u:umsAdmins
             ) {
            System.out.println(u);
        }
    }

    @Test
    public void insert(){
        UmsAdmin umsAdmin=new UmsAdmin();
        umsAdmin.setUsername("laoji");
        umsAdmin.setPassword(passwordEncoder.encode("123456"));
        umsAdmin.setIcon("");
        umsAdmin.setEmail("");
        umsAdmin.setNickName("");
        umsAdmin.setNote("");
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setLoginTime(new Date());
        umsAdmin.setStatus(0);
        int result = umsAdminService.insert(umsAdmin);
        Assert.assertEquals(result,1);
    }

}
