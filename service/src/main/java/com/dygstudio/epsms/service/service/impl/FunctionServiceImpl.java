package com.dygstudio.epsms.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dygstudio.epsms.service.common.CommonUtils;
import com.dygstudio.epsms.service.entity.*;
import com.dygstudio.epsms.service.mapper.FunctionMapper;
import com.dygstudio.epsms.service.mapper.RoleFunctionLinkMapper;
import com.dygstudio.epsms.service.service.FunctionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
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
    @Resource
    RoleFunctionLinkMapper roleFunctionLinkMapper;

    public List<Function> getFunctionByUserId(String userId) {
        return functionMapper.getFunctionByUserId(userId);
    }

    public List<Function> buildUserMenuFunctions(List<Function> userFunctions) {
        List<Function> menuFunctions = new ArrayList<>();
        //从数据库中拿出一级菜单
        QueryWrapper query = new QueryWrapper();
        query.eq("level",1);
        List<Function> topMenuFunctions = functionMapper.selectList(query);
        for(Function topItem : topMenuFunctions){
            if(userFunctions.stream().filter(a->a.getParentId()!=null && a.getParentId().equals(topItem.getId())).collect(Collectors.toList()).size()>0){
                buildSubFunctions(topItem, userFunctions);
                menuFunctions.add(topItem);
            }
        }
        /*//组织功能菜单
        menuFunctions = userFunctions.stream().filter(a -> a.getLevel() == 1).collect(Collectors.toList());
        for (Function tmpMainItem : menuFunctions) {
            buildSubFunctions(tmpMainItem, userFunctions);
        }*/
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

    public IPage<Function> getFunctionListByPage(Integer currentPage, Integer pageSize){
        Page page = new Page(currentPage,pageSize);
        return functionMapper.selectPage(page,new QueryWrapper<>());
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

    public boolean modifyRoleFunctionLinkByRoleId(String roleId, List<String> funcIds){
        //先删除原有关联记录
        roleFunctionLinkMapper.deleteByRoleId(roleId);
        //插入新的关联关系
        RoleFunctionLink link  = new RoleFunctionLink();
        for(String funcId : funcIds){
            link.setId(CommonUtils.GenerateId());
            link.setFuncId(funcId);
            link.setRoleId(roleId);
            roleFunctionLinkMapper.insert(link);
        }
        return true;
    }

    @Transactional
    public Integer deleteFunctionById(String functionId){
        Integer result = functionMapper.deleteById(functionId);
        if(result==1){
            roleFunctionLinkMapper.deleteByFuncId(functionId);
        }
        return result;
    }

    @Transactional
    public Integer deleteFunctionList(List<Function> functionList){
        Integer resultCount = 0;
        for(Function function : functionList){
            resultCount+=deleteFunctionById(function.getId());
        }
        return resultCount;
    }

    public IPage<Function> findAllFunctionForShow(Integer page, Integer pageSize){
        Page<Function> queryPage = new Page(page,pageSize);
        List<Function> result = functionMapper.findAllFunctionForShow(queryPage);
        queryPage.setRecords(result);
        return queryPage;
    }

}
