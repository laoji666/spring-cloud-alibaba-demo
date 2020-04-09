package com.laoji.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
*  修改密码DTO
*  @author: laoji
*  @date:2020/4/9 22:17
*/
@Data
public class PasswordParam implements Serializable {

    private static final long serialVersionUID = 4519910614292427867L;

    private String userName;
    private String oldPassword;
    private String newPassword;
}
