package com.dygstudio.epsms.service.vo;

import java.util.List;

/**
 * 〈功能概述〉
 *
 * @className: FunctionVo
 * @package: com.dygstudio.epsms.service.vo
 * @author: diyaguang
 * @date: 2020/5/15 10:35
 */
public class FunctionVo {
    private String key;
    private String label;
    private boolean disabled;
    private List<FunctionVo> children;

    public List<FunctionVo> getChildren() {
        return children;
    }

    public void setChildren(List<FunctionVo> children) {
        this.children = children;
    }

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
}
