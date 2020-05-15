package com.dygstudio.epsms.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dygstudio.epsms.service.entity.Role;
import com.dygstudio.epsms.service.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    Role findRolsById(String roleId);
    void insertRoleFunctionLink(Map<String,String> param);
    void deleteRoleFuncionLinkByRoleId(String roleId);
    Set<Role> findRolesByUserId(String id);
    List<Role> findAllRoleForShow(Page<Role> page);
}
