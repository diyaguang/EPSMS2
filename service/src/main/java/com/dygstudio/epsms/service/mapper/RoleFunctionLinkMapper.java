package com.dygstudio.epsms.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dygstudio.epsms.service.entity.RoleFunctionLink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleFunctionLinkMapper extends BaseMapper<RoleFunctionLink> {
    int deleteByRoleId(String roleId);
    int deleteByFuncId(String funcId);
    List<RoleFunctionLink> findFunctionByRoleId(String roleId);
}
