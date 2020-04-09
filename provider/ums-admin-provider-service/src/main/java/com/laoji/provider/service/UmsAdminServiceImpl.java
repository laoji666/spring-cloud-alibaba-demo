package com.laoji.provider.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.laoji.provider.api.UmsAdminService;
import com.laoji.provider.domain.UmsAdmin;
import com.laoji.provider.mapper.UmsAdminMapper;
import com.laoji.provider.service.fallback.UmsAdminServiceFallback;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
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
    @SentinelResource(value = "getByUserName",fallback = "getByUsernameFallback",fallbackClass = UmsAdminServiceFallback.class)
    public UmsAdmin get(String username) {
        // 增加一段异常代码，用于测试熔断
        Example example=new Example(UmsAdmin.class);
        example.createCriteria().andEqualTo("username",username);
        return umsAdminMapper.selectOneByExample(example);
    }

    @Override
    public UmsAdmin get(UmsAdmin umsAdmin) {
        return umsAdminMapper.selectOne(umsAdmin);
    }

    @Override
    public int update(UmsAdmin umsAdmin) {
        //获取用户原始信息
        UmsAdmin oldUmsAdmin=get(umsAdmin.getUsername());
        //修改信息
        oldUmsAdmin.setEmail(umsAdmin.getEmail());
        oldUmsAdmin.setNickName(umsAdmin.getNickName());
        oldUmsAdmin.setNote(umsAdmin.getNote());
        oldUmsAdmin.setStatus(umsAdmin.getStatus());
        return umsAdminMapper.updateByPrimaryKey(oldUmsAdmin);
    }

    @Override
    public int updateIcon(String userName, String path) {
        //获取用户原始信息
        UmsAdmin oldUmsAdmin=get(userName);

        oldUmsAdmin.setIcon(path);

        return umsAdminMapper.updateByPrimaryKey(oldUmsAdmin);
    }

    @Override
    public int updatePassword(String userName, String newPassword) {
        UmsAdmin umsAdmin = get(userName);
        umsAdmin.setPassword(passwordEncoder.encode(newPassword));
        return umsAdminMapper.updateByPrimaryKey(umsAdmin);
    }
}
