package com.dygstudio.epsms.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dygstudio.epsms.service.mapper.FunctionMapper;
import com.dygstudio.epsms.service.entity.Function;
import com.dygstudio.epsms.service.service.FunctionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 〈功能概述〉
 *
 * @className: FunctionServiceImpl
 * @package: com.dygstudio.epsms.service.service.impl
 * @author: diyaguang
 * @date: 2020/1/2 21:18
 */
@Service
public class FunctionServiceImpl extends ServiceImpl<FunctionMapper, Function> implements FunctionService {
    @Resource
    FunctionMapper functionMapper;

    public List<Function> getFunctionByUserId(String userId) {
        return functionMapper.getFunctionByUserId(userId);
    }

    public List<Function> buildUserMenuFunctions(List<Function> userFunctions) {
        //组织功能菜单
        List<Function> menuFunctions = userFunctions.stream().filter(a -> a.getLevel() == 1).collect(Collectors.toList());
        for (Function tmpMainItem : menuFunctions) {
            buildSubFunctions(tmpMainItem, userFunctions);
        }
        menuFunctions.sort(Comparator.comparing(Function::getSort));
        return menuFunctions;
    }

    public void buildSubFunctions(Function root, List<Function> sources) {
        List<Function> tmpSubFunctions = sources.stream().filter(a -> a.getParentId() != null && a.getParentId().equals(root.getId())).collect(Collectors.toList());
        for (Function tmpSubItem : tmpSubFunctions) {
            buildSubFunctions(tmpSubItem, sources);
        }
        tmpSubFunctions.sort(Comparator.comparing(Function::getSort));
        root.setSubFunction(tmpSubFunctions);
    }

    public List<Function> getFunctionList() {
        Page page = new Page(0,2);
        return functionMapper.selectPage(page,new QueryWrapper<>()).getRecords();
    }

    public Function getFunctionById(String functionId) {
        return functionMapper.getFunctionById(functionId);
    }

    public List<Function> getFunctionByConditions(Function function) {
        return functionMapper.getFunctionByConditions(function);
    }

    public List<Function> getFunctionByRoleId(String roleId){
        return functionMapper.getFunctionByRoleId(roleId);
    }
}
