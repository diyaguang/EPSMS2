package com.dygstudio.epsms.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dygstudio.epsms.service.entity.RoleFunctionLink;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleFunctionLinkMapper extends BaseMapper<RoleFunctionLink> {
    int deleteByRoleId(String roleId);
    int deleteByFuncId(String funcId);
}
