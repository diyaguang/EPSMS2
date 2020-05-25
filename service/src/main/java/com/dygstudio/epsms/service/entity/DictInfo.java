package com.dygstudio.epsms.service.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 〈功能概述〉
 *
 * @className: DictInfo
 * @package: com.dygstudio.epsms.service.entity
 * @author: diyaguang
 * @date: 2020/1/2 20:34
 */
@TableName("DictInfo")
public class DictInfo {
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    //@Id
    private String id;
    private String name;
    private String parentId;
    private String value;

    @TableField(exist = false)
    private List<DictInfo> childs;

    public List<DictInfo> getChilds() {
        return childs;
    }

    public void setChilds(List<DictInfo> childs) {
        this.childs = childs;
    }
}
