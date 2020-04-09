package com.laoji.business.service;

import com.google.common.collect.Lists;
import com.laoji.provider.api.UmsAdminService;
import com.laoji.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 自定义认证授权
 * <p>
 * Description:
 * </p>
 *  @author: laoji
 *  @date:2020/4/5 14:38
 *
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Reference(version = "1.0.0")
    UmsAdminService umsAdminService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //默认授权USER
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        grantedAuthorities.add(grantedAuthority);

        UmsAdmin umsAdmin = umsAdminService.get(s);

        // 用户名匹配
        if (umsAdmin!=null) {
            return new User(umsAdmin.getUsername(), umsAdmin.getPassword(), grantedAuthorities);
        }
        // 用户名不匹配
        else {
            return null;
        }
    }

}
