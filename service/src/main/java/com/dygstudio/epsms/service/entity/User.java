package com.dygstudio.epsms.service.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.Set;

/**
 * 〈功能概述〉
 *
 * @className: User
 * @package: com.dygstudio.epsms.service.entity
 * @author: diyaguang
 * @date: 2019-12-30 17:25
 */
@TableName("User")
public class User {
    @TableId()
    private String id;
    private String name;
    private String password;

    @TableField(exist = false)
    private Set<Role> roles;

    private String opUserId;

    @TableField(exist = false)
    private User opUserVo;

    private Date opDate;
    private Integer status;

    @TableLogic
    private Integer isDel;

    @TableField(exist = false)
    private DictInfo statusVo;

    private String description;
    private String companyId;
    private String Code;
    private Integer sort;

    public User getOpUserVo() {
        return opUserVo;
    }

    public void setOpUserVo(User opUserVo) {
        this.opUserVo = opUserVo;
    }

    public DictInfo getStatusVo() {
        return statusVo;
    }

    public void setStatusVo(DictInfo statusVo) {
        this.statusVo = statusVo;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
