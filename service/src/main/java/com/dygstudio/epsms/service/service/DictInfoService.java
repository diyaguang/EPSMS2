package com.dygstudio.epsms.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dygstudio.epsms.service.entity.DictInfo;

import java.util.List;

public interface DictInfoService extends IService<DictInfo> {
    DictInfo findObjectAndChildByValue(String value);
}
