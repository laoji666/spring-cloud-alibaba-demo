package com.laoji.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
*  修改头像 dto
*  @author: laoji
*  @date:2020/4/8 23:04
*/
@Data
public class IconParam implements Serializable {

    private static final long serialVersionUID = -789182328321590957L;
    private String username;
    private String path;
}
