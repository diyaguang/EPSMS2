package com.dygstudio.epsms.service.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.interfaces.Func;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dygstudio.epsms.service.common.CommonUtils;
import com.dygstudio.epsms.service.common.PageResult;
import com.dygstudio.epsms.service.common.SysConstant;
import com.dygstudio.epsms.service.entity.*;
import com.dygstudio.epsms.service.service.*;
import com.dygstudio.epsms.service.vo.DictVo;
import com.dygstudio.epsms.service.vo.FunctionVo;
import com.dygstudio.epsms.service.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 〈功能概述〉
 *
 * @className: SystemController
 * @package: com.dygstudio.epsms.service.controller
 * @author: diyaguang
 * @date: 2020/4/24 16:59
 */
@Controller
@RequestMapping(value = "/api/system")
public class SystemController {

    @Resource
    FunctionService functionService;
    @Resource
    RoleService roleService;
    @Resource
    RoleFunctionLinkService roleFunctionLinkService;
    @Resource
    DictInfoService dictInfoService;


    /**
     * 功能描述:
     * @Param: [page, pageSize]
     * @Return: com.dygstudio.epsms.service.common.PageResult<com.dygstudio.epsms.service.entity.Function>
     * @Author: diyaguang
     * @Date: 2020/4/28 9:54
     * http://localhost:8090/api/system/function/list?page=1&pageSize=10
     */
    @ResponseBody
    @RequestMapping(value = "/function/list")
    public PageResult<Function> getFunctionList(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize){
        IPage<Function> result =functionService.getFunctionListByPage(page,pageSize);
        return new PageResult<Function>(result.getTotal(),result.getRecords());
    }

    @ResponseBody
    @RequestMapping(value = "/function/listForOp")
    public List<FunctionVo> getFunctionListForOp(){
        List<FunctionVo> result = new ArrayList<>();
        List<Function> sources = functionService.list();
        List<Function> topFunction = sources.stream().filter(a -> a.getLevel() == 1).collect(Collectors.toList());
        for(Function tmpSubItem : topFunction){
            FunctionVo tmpItem = new FunctionVo();
            tmpItem.setKey(tmpSubItem.getId());
            tmpItem.setLabel(tmpSubItem.getFuncName());
            tmpItem.setDisabled(false);
            result.add(tmpItem);
            buildChildren(tmpItem, sources);
        }
        return result;
    }

    @RequestMapping(value = "/function/listShow")
    @ResponseBody
    public PageResult<Function> getFunctionListForShow(@RequestParam("page") Integer currentPage, @RequestParam("pageSize") Integer pageSize){
        IPage<Function> result = functionService.findAllFunctionForShow(currentPage,pageSize);
        return new PageResult<Function>(result.getTotal(),result.getRecords());
    }

    private void buildChildren(FunctionVo funcItem,List<Function> sources){
        List<Function> tmpSubFunctions = sources.stream().filter(a -> a.getParentId() != null && a.getParentId().equals(funcItem.getKey())).collect(Collectors.toList());
        List<FunctionVo> tmpChildren = new ArrayList<>();
        for (Function tmpSubItem : tmpSubFunctions) {
            FunctionVo tmpItem = new FunctionVo();
            tmpItem.setKey(tmpSubItem.getId());
            tmpItem.setLabel(tmpSubItem.getFuncName());
            tmpItem.setDisabled(false);
            tmpChildren.add(tmpItem);
            buildChildren(tmpItem, sources);
        }
        funcItem.setChildren(tmpChildren);
    }

    @ResponseBody
    @RequestMapping(value = "/function/listForList")
    public List<FunctionVo> getFunctionListForList(){
        List<FunctionVo> result = new ArrayList<>();
        FunctionVo topFunction = new FunctionVo();
        topFunction.setKey("");
        topFunction.setLabel("顶级目录");
        result.add(topFunction);
        List<Function> sources = functionService.list();
        List<Function> topFunctions = sources.stream().filter(a -> a.getLevel() == 1).collect(Collectors.toList());
        for(Function tmpSubItem : topFunctions){
            FunctionVo tmpItem = new FunctionVo();
            tmpItem.setKey(tmpSubItem.getId());
            tmpItem.setLabel(tmpSubItem.getFuncName());
            tmpItem.setDisabled(false);
            result.add(tmpItem);
            result.addAll(buildChildrenForList(tmpItem, sources));
        }
        return result;
    }

    private List<FunctionVo> buildChildrenForList(FunctionVo funcItem,List<Function> sources){
        List<FunctionVo> result = new ArrayList<>();
        List<Function> tmpSubFunctions = sources.stream().filter(a -> a.getParentId() != null && a.getParentId().equals(funcItem.getKey())).collect(Collectors.toList());
        for (Function tmpSubItem : tmpSubFunctions) {
            FunctionVo tmpItem = new FunctionVo();
            tmpItem.setKey(tmpSubItem.getId());
            String labelName = tmpSubItem.getFuncName();
            if(tmpSubItem.getLevel() == 2)
                labelName = "-->"+labelName;
            else if(tmpSubItem.getLevel() == 3)
                labelName = "---->"+labelName;
            tmpItem.setLabel(labelName);
            tmpItem.setDisabled(false);
            result.add(tmpItem);
            result.addAll(buildChildrenForList(tmpItem, sources));
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/function/insert",method = RequestMethod.POST)
    public PageResult<Function> insertFunction(@RequestBody Function function){
        PageResult<Function> result = new PageResult<>();
        function.setId(UUID.randomUUID().toString().replaceAll("-",""));  //设置对象新的GUID
        //设置父对象
        if(StrUtil.isBlank(function.getParentId())){
            function.setLevel(1);
        }else{
            Function parentItem = functionService.getFunctionById(function.getParentId());
            if(parentItem!=null){
                function.setLevel(parentItem.getLevel()+1);
            }else {
                function.setLevel(1);
            }
        }
        boolean opResult = functionService.save(function);
        if(opResult){
            result.setCode(SysConstant.RESULT_CODE_SUCCESSFUL);
            result.setMsg(function.getId()); //插入比较特殊，返回新对象的 ID作为成功的返回值
            result.setData(null);
            result.setCount(1);
        }else{
            result.setCode(SysConstant.RESULT_CODE_FAILURE);
            result.setMsg(SysConstant.RESULT_MSG_FAILURE);
            result.setData(null);
            result.setCount(0);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/function/update",method = RequestMethod.POST)
    public PageResult<Function> updateFunction(@RequestBody Function function){
        PageResult<Function> result = new PageResult<>();
        //设置父对象
        if(StrUtil.isBlank(function.getParentId())){
            function.setLevel(1);
        }else{
            Function parentItem = functionService.getFunctionById(function.getParentId());
            if(parentItem!=null){
                function.setLevel(parentItem.getLevel()+1);
            }else {
                function.setLevel(1);
            }
        }
        boolean opResult = functionService.updateById(function);
        if(opResult){
            result.setCode(SysConstant.RESULT_CODE_SUCCESSFUL);
            result.setMsg(SysConstant.RESULT_MSG_SUCCESSFUL);
            result.setData(null);
            result.setCount(1);
        }else{
            result.setCode(SysConstant.RESULT_CODE_FAILURE);
            result.setMsg(SysConstant.RESULT_MSG_FAILURE);
            result.setData(null);
            result.setCount(0);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/function/delete")
    public PageResult<Function> deleteFunction(@RequestParam("functionId") String functionId){
        PageResult<Function> result = new PageResult<>();
        Integer opResult = functionService.deleteFunctionById(functionId);
        if(opResult>0){
            result.setCode(SysConstant.RESULT_CODE_SUCCESSFUL);
            result.setMsg(SysConstant.RESULT_MSG_SUCCESSFUL);
            result.setData(null);
            result.setCount(1);
        }else{
            result.setCode(SysConstant.RESULT_CODE_FAILURE);
            result.setMsg(SysConstant.RESULT_MSG_FAILURE);
            result.setData(null);
            result.setCount(0);
        }
        return result;
    }

    @RequestMapping(value = "/role/list")
    @ResponseBody
    public PageResult<Role> getRoleList(@RequestParam("page") Integer currentPage, @RequestParam("pageSize") Integer pageSize){
        Page page = new Page(currentPage,pageSize);
        List<Role> result = roleService.page(page).getRecords();
        int recordCount = roleService.count();
        return new PageResult<Role>(recordCount,result);
    }

    @RequestMapping(value = "/role/listShow")
    @ResponseBody
    public PageResult<Role> getRoleListForShow(@RequestParam("page") Integer currentPage, @RequestParam("pageSize") Integer pageSize){
        IPage<Role> result = roleService.findAllRoleForShow(currentPage,pageSize);
        return new PageResult<Role>(result.getTotal(),result.getRecords());
    }

    @RequestMapping(value = "/role/listForOp")
    @ResponseBody
    public List<RoleVo> getRoleListForOp(){
        List<RoleVo> result = new ArrayList<>();
        List<Role> roles = roleService.list();
        for(Role item : roles){
            RoleVo vo = new RoleVo();
            vo.setKey(item.getId());
            vo.setLabel(item.getRoleName());
            vo.setDisabled(false);
            result.add(vo);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/role/insert",method = RequestMethod.POST)
    public PageResult<Role> insertRole(@RequestBody Role role){
        PageResult<Role> result = new PageResult<>();
        role.setId(CommonUtils.GenerateId());
        role.setOpDate(new Date());
        boolean opResult = roleService.save(role);
        if(opResult){
            result.setCode(SysConstant.RESULT_CODE_SUCCESSFUL);
            result.setMsg(role.getId()); //插入比较特殊，返回新对象的 ID作为成功的返回值
            result.setData(null);
            result.setCount(1);
        }else{
            result.setCode(SysConstant.RESULT_CODE_FAILURE);
            result.setMsg(SysConstant.RESULT_MSG_FAILURE);
            result.setData(null);
            result.setCount(0);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/role/update",method = RequestMethod.POST)
    public PageResult<Role> updateRole(@RequestBody Role role){
        PageResult<Role> result = new PageResult<>();
        boolean opResult = roleService.updateById(role);
        if(opResult){
            result.setCode(SysConstant.RESULT_CODE_SUCCESSFUL);
            result.setMsg(SysConstant.RESULT_MSG_SUCCESSFUL);
            result.setData(null);
            result.setCount(1);
        }else{
            result.setCode(SysConstant.RESULT_CODE_FAILURE);
            result.setMsg(SysConstant.RESULT_MSG_FAILURE);
            result.setData(null);
            result.setCount(0);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/role/delete")
    public PageResult<Role> deleteRole(@RequestParam("roleId") String roleId){
        PageResult<Role> result = new PageResult<>();
        Integer opResult = roleService.deleteById(roleId);
        if(opResult>0){
            result.setCode(SysConstant.RESULT_CODE_SUCCESSFUL);
            result.setMsg(SysConstant.RESULT_MSG_SUCCESSFUL);
            result.setData(null);
            result.setCount(1);
        }else{
            result.setCode(SysConstant.RESULT_CODE_FAILURE);
            result.setMsg(SysConstant.RESULT_MSG_FAILURE);
            result.setData(null);
            result.setCount(0);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/role/roleFunctionLink/modify",method = RequestMethod.POST)
    public PageResult<RoleFunctionLink> modifyRoleFunctionLink(@RequestParam("roleId") String roleId,@RequestBody List<String> funcIds){
        boolean result = functionService.modifyRoleFunctionLinkByRoleId(roleId,funcIds);
        PageResult<RoleFunctionLink> pageResult = new PageResult<>();
        if(result) {
            pageResult.setCode(SysConstant.RESULT_CODE_SUCCESSFUL);
            pageResult.setMsg(SysConstant.RESULT_MSG_SUCCESSFUL);
            pageResult.setData(null);
            pageResult.setCount(funcIds.size());
        }else {
            pageResult.setCode(SysConstant.RESULT_CODE_FAILURE);
            pageResult.setMsg(SysConstant.RESULT_MSG_FAILURE);
            pageResult.setData(null);
            pageResult.setCount(funcIds.size());
        }
        return pageResult;
    }

    @ResponseBody
    @RequestMapping(value = "/role/roleFunctionLink/list")
    public PageResult<RoleFunctionLink> getRoleFunctionLinkByRoleId(@RequestParam("roleId") String roleId){
        List<RoleFunctionLink> result = roleFunctionLinkService.getRoleFunctionLinkByRoleId(roleId);
        return new PageResult<RoleFunctionLink>(result.size(),result);
    }

    /**
     * 功能描述: 根据传入的 Dict-value 来获取子对象值。
     * http://localhost:8090/api/system/dict/objectAndChildForValue?value=200
     * @Param: [value]
     * @Return: com.dygstudio.epsms.service.vo.DictVo
     * @Author: diyaguang
     * @Date: 2020/5/26 10:28
     */
    @ResponseBody
    @RequestMapping(value = "/dict/objectAndChildForValue")
    public DictVo getDictForValue(@RequestParam("value") String value){
        DictInfo dictInfo = dictInfoService.findObjectAndChildByValue(value);
        DictVo dictInfoVo = new DictVo();
        dictInfoVo.setKey(dictInfo.getValue());
        dictInfoVo.setLabel(dictInfo.getName());
        dictInfoVo.setDisabled(false);
        dictInfoVo.setChildren(buildDictChild(dictInfo));
        return dictInfoVo;
    }

    public List<DictVo> buildDictChild(DictInfo dictInfo){
        List<DictVo> result = null;
        if(dictInfo.getChilds() !=null ) {
            result = new ArrayList<>();
            for (DictInfo childItem : dictInfo.getChilds()) {
                DictVo dictInfoVo = new DictVo();
                dictInfoVo.setKey(childItem.getValue());
                dictInfoVo.setLabel(childItem.getName());
                dictInfoVo.setDisabled(false);
                dictInfoVo.setValue(childItem.getValue());
                dictInfoVo.setChildren(buildDictChild(childItem));
                result.add(dictInfoVo);
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/dict/dictForTop")
    public List<DictVo> getDictForTop(){
        List<DictVo> result = new ArrayList<>();
        List<DictInfo> dictInfoList = dictInfoService.findByTopObject();
        for(DictInfo info : dictInfoList){
            DictVo dictInfoVo = new DictVo();
            dictInfoVo.setKey(info.getId());
            dictInfoVo.setLabel(info.getName());
            dictInfoVo.setValue(info.getValue());
            dictInfoVo.setChildren(buildDictChild(info));
            result.add(dictInfoVo);
        }
        return result;
    }

}
