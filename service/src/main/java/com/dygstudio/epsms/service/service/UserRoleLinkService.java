package com.dygstudio.epsms.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dygstudio.epsms.service.entity.UserRoleLink;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Max;
import java.util.List;

@Service
public interface UserRoleLinkService extends IService<UserRoleLink> {
    int  deleteByUserId(String userId);
    int deleteByRoleId(String roleId);
    boolean addUserRoleLink(List<UserRoleLink> links);
    List<UserRoleLink> getUserRoleLinkByUserId(String userId);
}
