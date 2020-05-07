package com.dygstudio.epsms.service.vo;

/**
 * 〈功能概述〉
 *
 * @className: RoleVo
 * @package: com.dygstudio.epsms.service.vo
 * @author: diyaguang
 * @date: 2020/5/7 9:04
 */
public class RoleVo {
    private String key;
    private String label;
    private boolean disabled;

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
