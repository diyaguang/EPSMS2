package com.dygstudio.epsms.service.vo;

import java.util.List;

/**
 * 〈功能概述〉
 *
 * @className: DictVo
 * @package: com.dygstudio.epsms.service.vo
 * @author: diyaguang
 * @date: 2020/5/25 21:01
 */
public class DictVo {
    private String key;
    private String label;
    private boolean disabled;
    private List<DictVo> children;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public List<DictVo> getChildren() {
        return children;
    }

    public void setChildren(List<DictVo> children) {
        this.children = children;
    }
}
