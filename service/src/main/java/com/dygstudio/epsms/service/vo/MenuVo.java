package com.dygstudio.epsms.service.vo;

import java.util.List;

/**
 * 〈功能概述〉
 *
 * @className: MenuVo
 * @package: com.dygstudio.epsms.service.vo
 * @author: diyaguang
 * @date: 2020/4/28 11:29
 */
public class MenuVo {
    private String id;
    private String sId;
    private String name;
    private String index;
    private String path;
    private String type;
    private String url;
    private String iocName;
    private String componentName;
    private List<MenuVo> childs;

    public List<MenuVo> getChilds() {
        return childs;
    }

    public void setChilds(List<MenuVo> childs) {
        this.childs = childs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIocName() {
        return iocName;
    }

    public void setIocName(String iocName) {
        this.iocName = iocName;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }
}
