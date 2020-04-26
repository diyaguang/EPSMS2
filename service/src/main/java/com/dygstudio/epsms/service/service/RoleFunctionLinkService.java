package com.dygstudio.epsms.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dygstudio.epsms.service.entity.RoleFunctionLink;

public interface RoleFunctionLinkService extends IService<RoleFunctionLink> {
    int  deleteByRoleId(String roleId);
    int deleteByFuncId(String funcId);
}
