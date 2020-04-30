package com.dygstudio.epsms.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dygstudio.epsms.service.entity.Role;
import com.dygstudio.epsms.service.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService extends IService<Role> {
    Integer deleteById(String roleId);
    Integer deleteRoleList(List<Role> roleList);
}
