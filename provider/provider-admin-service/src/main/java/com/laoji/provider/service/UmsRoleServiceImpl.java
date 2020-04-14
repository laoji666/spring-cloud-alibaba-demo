package com.laoji.provider.service;

import com.laoji.provider.api.UmsRoleService;
import com.laoji.provider.domain.UmsRole;
import com.laoji.provider.mapper.UmsRoleMapper;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
*  角色表服务
*  @author: laoji
*  @date:2020/4/14 16:21
*/
@Service
public class UmsRoleServiceImpl implements UmsRoleService{

    @Resource
    private UmsRoleMapper umsRoleMapper;


    @Override
    public List<UmsRole> getAll() {
        return umsRoleMapper.selectAll();
    }
}
