package com.dygstudio.epsms.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dygstudio.epsms.service.entity.UserRoleLink;
import org.springframework.stereotype.Service;

@Service
public interface UserRoleLinkService extends IService<UserRoleLink> {
    int  deleteByUserId(String userId);
    int deleteByRoleId(String roleId);
}
