package com.laoji.business.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.laoji.business.BusinessException;
import com.laoji.business.BusinessStatus;
import com.laoji.commons.dto.ResponseResult;
import com.laoji.provider.api.UmsAdminService;
import com.laoji.provider.api.UmsRoleService;
import com.laoji.provider.domain.UmsAdmin;
import com.laoji.provider.domain.UmsRole;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
*  系统用户controller
*  @author: laoji
*  @date:2020/4/14 14:23
*/
@RestController
@RequestMapping(value="/system/user")
public class SystemUserController {

    @Reference(version = "1.0.0")
    public UmsAdminService umsAdminService;
    @Reference
    public UmsRoleService umsRoleService;
    @GetMapping(value = "/getAll")
    public ResponseResult<PageInfo> getAll(Integer pageSize, Integer pageNum){
        PageInfo<UmsAdmin> pageInfo = umsAdminService.getAll(pageSize,pageNum);
        if(pageInfo==null){
            throw new BusinessException(BusinessStatus.DATA_NOT_FOUNT);
        }
        return new ResponseResult<PageInfo>(ResponseResult.OK,"查询成功",pageInfo);
    }
    @GetMapping(value="/getRoleList")
    public ResponseResult<List<UmsRole>> getRoleList(){
        List<UmsRole> roleList = umsRoleService.getAll();
        return new ResponseResult<List<UmsRole>>(ResponseResult.OK,"查询成功",roleList);
    }
    @GetMapping(value="/getRoleByAdminId")
    public ResponseResult<List<Integer>> getRoleByAdminId(Integer id){
        if(id==null){
            throw new BusinessException(BusinessStatus.ILLEGAL_REQUEST);
        }
        List<Integer> roleLists = umsAdminService.getRoleByAdminId(id);
        List<Integer> emptyList= Lists.newArrayList();
        if(roleLists.get(0)==null){
            return new ResponseResult<List<Integer>>(ResponseResult.OK,"查询成功",emptyList);
        }
        return new ResponseResult<List<Integer>>(ResponseResult.OK,"查询成功",roleLists);
    }

    @PostMapping(value = "/updateRole")
    @PreAuthorize("hasAuthority('ums:admin:update')")
    public ResponseResult<Void> updateRole(@RequestBody List<Integer> newList,Integer id){
        System.out.println(newList);
        System.out.println(id);
        boolean b = umsAdminService.updateRole(newList, id);
        if(b){
            return new ResponseResult<Void>(ResponseResult.OK,"修改成功");
        }else{
            return new ResponseResult<Void>(ResponseResult.FAIL,"修改失败");
        }
    }
    @PostMapping(value="/addAdmin")
    @PreAuthorize("hasAuthority('ums:admin:create')")
    public ResponseResult<Void> addAdmin(@RequestBody UmsAdmin umsAdmin){
        String message=validateReg(umsAdmin);
        if(message==null){
            //验证通过
            int result = umsAdminService.insert(umsAdmin);
            if(result>0){
                //注册成功
                return new ResponseResult<Void>(ResponseResult.OK,"添加成功！");
            }
        }
        return new ResponseResult<Void>(ResponseResult.FAIL,message != null ? message : "添加新用户失败");
    }

    /**
     * 验证注册信息
     * @param umsAdmin {@link UmsAdmin}
     * @return 错误信息
     */
    private String validateReg(UmsAdmin umsAdmin) {
        if(umsAdmin.getUsername()==null||umsAdmin.getUsername().length()<6){
            return "用户名格式有误或者为空，用户名不能低于六位字符";
        }
        if(umsAdmin.getPassword()==null||umsAdmin.getPassword().length()<6){
            return "密码格式有误或者为空，密码不能低于六位字符";
        }
        UmsAdmin admin = umsAdminService.get(umsAdmin.getUsername());
        if (admin != null) {
            return "用户名已存在，请重新输入";
        }
        return null;
    }
}
