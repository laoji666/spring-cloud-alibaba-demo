package com.laoji.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
*  个人信息dto
*  @author: laoji
*  @date:2020/4/8 16:05
*/
@Data
public class ProfileParam implements Serializable {

    private static final long serialVersionUID = -4120802988420138841L;
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String icon;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 备注
     */
    private String note;

    /**
     * 用户状态 {@code Integer}
     * <p>
     * 0 - 禁止 <br />
     * 1 - 启用
     * </p>
     */
    private Integer status;
}
