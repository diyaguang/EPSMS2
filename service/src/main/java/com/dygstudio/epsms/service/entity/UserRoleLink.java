package com.dygstudio.epsms.service.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 〈功能概述〉
 *
 * @className: UserRoleLink
 * @package: com.dygstudio.epsms.service.entity
 * @author: diyaguang
 * @date: 2020/4/25 15:31
 */
@TableName("UserRoleLink")
public class UserRoleLink implements Serializable {
    @TableId()
    private String id;
    private String roleId;
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
