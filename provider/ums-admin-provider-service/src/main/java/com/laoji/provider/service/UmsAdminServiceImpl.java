package com.laoji.provider.service;

import com.laoji.provider.domain.UmsAdmin;
import javax.annotation.Resource;
import com.laoji.provider.mapper.UmsAdminMapper;
import com.laoji.provider.api.UmsAdminService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
*  系统管理服务实现.
*  @author: laoji
*  @date:2020/4/4 22:55
*/
@Service(version="1.0.0")
public class UmsAdminServiceImpl implements UmsAdminService{

    @Resource
    private UmsAdminMapper umsAdminMapper;
    @Resource
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public int insert(UmsAdmin umsAdmin) {
        //初始化用户对象
        initUmsAdmin(umsAdmin);
        return umsAdminMapper.insert(umsAdmin);
    }

    private void initUmsAdmin(UmsAdmin umsAdmin) {
        //初始化时间
        umsAdmin.setLoginTime(new Date());
        umsAdmin.setCreateTime(new Date());
        //初始化状态
        if(umsAdmin.getStatus()==null){
            //禁用
            umsAdmin.setStatus(0);
        }
        //密码加密
        umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));
    }

    @Override
    public UmsAdmin get(String username) {
        Example example=new Example(UmsAdmin.class);
        example.createCriteria().andEqualTo("username",username);
        return umsAdminMapper.selectOneByExample(example);
    }

    @Override
    public UmsAdmin get(UmsAdmin umsAdmin) {
        return umsAdminMapper.selectOne(umsAdmin);
    }
}
