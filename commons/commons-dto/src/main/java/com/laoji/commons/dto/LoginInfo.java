package com.laoji.commons.dto;

import lombok.Data;

import java.io.Serializable;

/**
*  登录信息
*  @author: laoji
*  @date:2020/4/5 20:12
*/
@Data
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = -2849188393844424359L;
    private String name;
    private String avatar;
}
