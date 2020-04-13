package com.laoji.provider.mapper;

import com.laoji.provider.domain.UmsAdmin;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface UmsAdminMapper extends MyMapper<UmsAdmin> {
    List<String> getPermissionValueByUserName(String username);
}
