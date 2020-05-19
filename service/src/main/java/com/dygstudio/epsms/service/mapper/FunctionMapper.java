package com.dygstudio.epsms.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dygstudio.epsms.service.entity.Function;
import com.dygstudio.epsms.service.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FunctionMapper extends BaseMapper<Function> {
    Function getFunctionById(String funcId);
    List<Function> getFunctionByParentId(String funcId);
    List<Function> getFunctionByRoleId(String roleId);
    List<Function> getFunctionByUserId(String userId);
    List<Function> getFunctionByConditions(Function function);
    List<Function> findAllFunctionForShow(Page<Function> page);
}
