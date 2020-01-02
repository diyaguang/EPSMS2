package com.dygstudio.epsms.service.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 〈功能概述〉
 *
 * @className: Role
 * @package: com.dygstudio.epsms.service.entity
 * @author: diyaguang
 * @date: 2020/1/2 20:50
 */
@TableName("Role")
public class Role {
    @TableId()
    private String id;
    private String roleName;
    private String userId;

    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private List<Permission> permissions;
    @TableField(exist = false)
    private List<Function> functions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }
}
