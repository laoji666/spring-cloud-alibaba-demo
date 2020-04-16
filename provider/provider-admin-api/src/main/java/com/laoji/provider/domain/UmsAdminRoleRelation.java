package com.laoji.provider.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
*
*  @author: laoji
*  @date:2020/4/16 11:07
*/
@Data
@Table(name = "ums_admin_role_relation")
public class UmsAdminRoleRelation implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "role_id")
    private Long roleId;

    private static final long serialVersionUID = 1L;
}
