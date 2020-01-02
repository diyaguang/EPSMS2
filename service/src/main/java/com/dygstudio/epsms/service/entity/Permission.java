package com.dygstudio.epsms.service.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 〈功能概述〉
 *
 * @className: Permission
 * @package: com.dygstudio.epsms.service.entity
 * @author: diyaguang
 * @date: 2020/1/2 20:49
 */
@TableName("Permission")
public class Permission {
    @TableId()
    private String id;
    private String permission;
    private String roleId;

    @TableField(exist = false)
    private Role role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
