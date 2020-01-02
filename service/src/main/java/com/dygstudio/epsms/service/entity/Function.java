package com.dygstudio.epsms.service.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 〈功能概述〉
 *
 * @className: Function
 * @package: com.dygstudio.epsms.service.entity
 * @author: diyaguang
 * @date: 2020/1/2 20:35
 */
@TableName("Function")
public class Function {
    @TableId()
    private String id;
    private String parentId;
    private String funcName;
    private String funcCode;
    private int type;
    private int level;
    private String urlPath;
    private int sort;
    private String imagePath;
    private String iconName;

    @TableField(exist = false)
    private Function parent;
    @TableField(exist = false)
    private List<Function> subFunction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncCode() {
        return funcCode;
    }

    public void setFuncCode(String funcCode) {
        this.funcCode = funcCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public Function getParent() {
        return parent;
    }

    public void setParent(Function parent) {
        this.parent = parent;
    }

    public List<Function> getSubFunction() {
        return subFunction;
    }

    public void setSubFunction(List<Function> subFunction) {
        this.subFunction = subFunction;
    }
}
