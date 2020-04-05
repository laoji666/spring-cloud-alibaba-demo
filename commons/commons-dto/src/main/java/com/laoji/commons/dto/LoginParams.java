package com.laoji.commons.dto;

import lombok.Data;

import java.io.Serializable;

/**
*  登录参数接收dto
*  @author: laoji
*  @date:2020/4/5 16:08
*/
@Data
public class LoginParams implements Serializable {

    private String username;
    private String password;
}
