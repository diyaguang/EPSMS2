package com.dygstudio.epsms.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dygstudio.epsms.service.entity.Function;

import java.util.List;

public interface FunctionService extends IService<Function> {
    List<Function> getFunctionByUserId(String userId);
    List<Function> buildUserMenuFunctions(List<Function> userFunctions);
    List<Function> getFunctionListByPage(Integer currentPage,Integer pageSize);
    Function getFunctionById(String functionId);
    List<Function> getFunctionByConditions(Function function);
    List<Function> getFunctionByRoleId(String roleId);
}
