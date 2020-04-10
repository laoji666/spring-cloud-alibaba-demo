package com.laoji.provider.service;

import com.laoji.provider.api.UmsAdminLoginLogService;
import com.laoji.provider.domain.UmsAdminLoginLog;
import com.laoji.provider.mapper.UmsAdminLoginLogMapper;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
/**
*  系统管理员登录日志服务实现
*  @author: laoji
*  @date:2020/4/10 21:34
*/
@Service
public class UmsAdminLoginLogServiceImpl implements UmsAdminLoginLogService{

    @Resource
    private UmsAdminLoginLogMapper umsAdminLoginLogMapper;

    @Override
    public int insert(UmsAdminLoginLog umsAdminLoginLog) {
        return umsAdminLoginLogMapper.insert(umsAdminLoginLog);
    }
}
