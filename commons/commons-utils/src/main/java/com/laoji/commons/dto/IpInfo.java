package com.laoji.commons.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * IP 地址信息
 * <p>
 * Description:
 * </p>
 *
*  @author: laoji
*  @date:2020/4/11 19:09
*/
@Data
public class IpInfo implements Serializable {
    private static final long serialVersionUID = -6246313701294144575L;
    private String ip;
    private String country;
    private String area;
    private String region;
    private String city;
    private String county;
    private String isp;
    private String country_id;
    private String area_id;
    private String region_id;
    private String city_id;
    private String county_id;
    private String isp_id;
}
