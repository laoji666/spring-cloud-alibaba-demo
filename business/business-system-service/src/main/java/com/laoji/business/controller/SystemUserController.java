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
}
