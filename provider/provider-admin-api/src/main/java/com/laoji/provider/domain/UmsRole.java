package com.laoji.provider.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "ums_role")
public class UmsRole implements Serializable {
    private static final long serialVersionUID = -4891717905310193399L;
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 后台用户数量
     */
    @Column(name = "admin_count")
    private Integer adminCount;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 启用状态：0->禁用；1->启用
     */
    @Column(name = "`status`")
    private Integer status;

    @Column(name = "sort")
    private Integer sort;

}
