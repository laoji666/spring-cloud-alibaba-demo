package com.laoji.provider.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.laoji.provider.api.UmsAdminService;
import com.laoji.provider.domain.UmsAdmin;
import com.laoji.provider.domain.UmsAdminRoleRelation;
import com.laoji.provider.mapper.UmsAdminMapper;
import com.laoji.provider.mapper.UmsAdminRoleRelationMapper;
import com.laoji.provider.mapper.UmsRoleMapper;
import com.laoji.provider.service.fallback.UmsAdminServiceFallback;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    private UmsRoleMapper umsRoleMapper;
    @Resource
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;
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

    @Override
    public List<String> getPermissionValueByUserName(String username) {
        return umsAdminMapper.getPermissionValueByUserName(username);
    }
    @Override
    public List<Integer> getRoleByAdminId(Integer adminId) {
        return umsAdminMapper.getRoleByAdminId(adminId);
    }

    @Override
    public PageInfo<UmsAdmin> getAll(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectAll();
        PageInfo<UmsAdmin> pageInfo=new PageInfo<UmsAdmin>(umsAdmins);
        return pageInfo;
    }

    @Override
    public boolean updateRole(List<Integer> newList, Integer adminId) {
        try{
            List<Integer> oldList1 = getRoleByAdminId(adminId);
            if(oldList1.get(0)==null){
                //说明之前是空  直接插入即可
                newList.forEach(n -> {
                    //根据adminId和roleId 插入
                    UmsAdminRoleRelation umsAdminRoleRelation=new UmsAdminRoleRelation();
                    umsAdminRoleRelation.setAdminId(Long.valueOf(adminId));
                    umsAdminRoleRelation.setRoleId(Long.valueOf(n));
                    umsAdminRoleRelationMapper.insert(umsAdminRoleRelation);
                });
                return true;
            }
            List<Integer> oldList2 = Lists.newArrayList();
            oldList2.addAll(oldList1);
            //取差集
            oldList1.removeAll(newList);
            //此时oldList1中只剩多余的  删除即可
            oldList1.forEach(o -> {
                //根据adminId和roleId 删除记录
                UmsAdminRoleRelation umsAdminRoleRelation=new UmsAdminRoleRelation();
                umsAdminRoleRelation.setAdminId(Long.valueOf(adminId));
                umsAdminRoleRelation.setRoleId(Long.valueOf(o));
                umsAdminRoleRelationMapper.delete(umsAdminRoleRelation);
            });
            newList.removeAll(oldList2);
            //此时newList中只剩一个还没加入的  插入即可
            newList.forEach(n -> {
                //根据adminId和roleId 插入
                UmsAdminRoleRelation umsAdminRoleRelation=new UmsAdminRoleRelation();
                umsAdminRoleRelation.setAdminId(Long.valueOf(adminId));
                umsAdminRoleRelation.setRoleId(Long.valueOf(n));
                umsAdminRoleRelationMapper.insert(umsAdminRoleRelation);
            });
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
