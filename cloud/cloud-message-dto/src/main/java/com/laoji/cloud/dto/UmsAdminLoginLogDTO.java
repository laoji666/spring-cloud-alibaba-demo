package com.laoji.cloud.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UmsAdminLoginLogDTO {

    private Long adminId;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String ip;

    private String address;

    private String userAgent;
}
