package com.dygstudio.epsms.service.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
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
public class User implements Serializable {
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

    private Integer age;
    private Date birthday;
    private String phone;
    private String iDCard;
    private String address;
    private String email;
    private String userName;
    private String PostCode;
    private String department;

    @TableField(exist = false)
    private DictInfo departmentVo;

    private Integer type;

    @TableField(exist = false)
    private DictInfo typeVo;

    private String position;

    @TableField(exist = false)
    private DictInfo positionVo;

    private Integer isAdmin;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getiDCard() {
        return iDCard;
    }

    public void setiDCard(String iDCard) {
        this.iDCard = iDCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

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

    public DictInfo getDepartmentVo() {
        return departmentVo;
    }

    public void setDepartmentVo(DictInfo departmentVo) {
        this.departmentVo = departmentVo;
    }

    public DictInfo getTypeVo() {
        return typeVo;
    }

    public void setTypeVo(DictInfo typeVo) {
        this.typeVo = typeVo;
    }

    public DictInfo getPositionVo() {
        return positionVo;
    }

    public void setPositionVo(DictInfo positionVo) {
        this.positionVo = positionVo;
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
