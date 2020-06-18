package com.dygstudio.epsms.service.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 〈功能概述〉
 *
 * @className: RoleFunctionLink
 * @package: com.dygstudio.epsms.service.entity
 * @author: diyaguang
 * @date: 2020/4/25 11:32
 */
@TableName("FuncRoleLink")
public class RoleFunctionLink  implements Serializable {
    @TableId()
    private String id;
    private String roleId;
    private String funcId;

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

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }
}
