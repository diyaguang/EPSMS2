package com.dygstudio.epsms.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dygstudio.epsms.service.entity.Function;
import com.dygstudio.epsms.service.entity.Role;

import java.util.List;

public interface FunctionService extends IService<Function> {
    List<Function> getFunctionByUserId(String userId);
    List<Function> buildUserMenuFunctions(List<Function> userFunctions);
    IPage<Function> getFunctionListByPage(Integer currentPage, Integer pageSize);
    Function getFunctionById(String functionId);
    List<Function> getFunctionByConditions(Function function);
    List<Function> getFunctionByRoleId(String roleId);
    Integer deleteFunctionById(String functionId);
    Integer deleteFunctionList(List<Function> functionList);
    boolean modifyRoleFunctionLinkByRoleId(String roleId, List<String> funcIds);
    IPage<Function> findAllFunctionForShow(Integer page, Integer pageSize);
}
