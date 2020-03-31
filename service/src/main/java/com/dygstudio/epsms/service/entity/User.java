package com.dygstudio.epsms.service.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
