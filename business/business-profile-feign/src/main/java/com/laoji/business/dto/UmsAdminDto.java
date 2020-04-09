package com.laoji.business.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
*  系统管理员DTO
*  @author: laoji
*  @date:2020/4/9 12:10
*/
@Data
public class UmsAdminDto implements Serializable {
    private String username;
    private String icon;
    private String email;
    private String nickName;
    private String note;
    private Date createTime;
    private Date loginTime;
    private Integer status;
}
