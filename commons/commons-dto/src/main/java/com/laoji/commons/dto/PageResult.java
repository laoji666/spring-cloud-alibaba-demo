package com.laoji.commons.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
*  公共页面返回dto
*  @author: laoji
*  @date:2020/4/13 18:33
*/
@Data
public class PageResult implements Serializable {
    private Integer pageSize;
    private Integer pageNum;
    private Integer total;
    private List<Object> list;
}
