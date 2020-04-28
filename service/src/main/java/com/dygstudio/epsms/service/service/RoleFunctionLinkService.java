package com.dygstudio.epsms.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dygstudio.epsms.service.entity.RoleFunctionLink;
import com.dygstudio.epsms.service.entity.UserRoleLink;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RoleFunctionLinkService extends IService<RoleFunctionLink> {
    int  deleteByRoleId(String roleId);
    int deleteByFuncId(String funcId);
    boolean addRoleFunctionLink(List<RoleFunctionLink> links);
    List<RoleFunctionLink> getRoleFunctionLinkByRoleId(String roleId);
}
