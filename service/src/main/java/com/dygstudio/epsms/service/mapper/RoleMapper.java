package com.dygstudio.epsms.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dygstudio.epsms.service.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    Role findRolsById(String roleId);
    List<Role> findRolesByUserId(String userId);
    void insertRoleFunctionLink(Map<String,String> param);
    void deleteRoleFuncionLinkByRoleId(String roleId);
}
