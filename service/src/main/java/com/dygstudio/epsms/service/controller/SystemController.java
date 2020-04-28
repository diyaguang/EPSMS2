package com.dygstudio.epsms.service.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dygstudio.epsms.service.common.CommonUtils;
import com.dygstudio.epsms.service.common.PageResult;
import com.dygstudio.epsms.service.common.SysConstant;
import com.dygstudio.epsms.service.entity.*;
import com.dygstudio.epsms.service.service.FunctionService;
import com.dygstudio.epsms.service.service.RoleFunctionLinkService;
import com.dygstudio.epsms.service.service.RoleService;
import com.dygstudio.epsms.service.service.UserRoleLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

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
    UserRoleLinkService userRoleLinkService;

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
        List<Function> result =functionService.getFunctionListByPage(page,pageSize);
        return new PageResult<Function>(result.size(),result);
    }

    @ResponseBody
    @RequestMapping(value = "/function/insert",method = RequestMethod.POST)
    public PageResult<Function> insertFunction(@RequestBody Function function){
        PageResult<Function> result = new PageResult<>();
        function.setId(UUID.randomUUID().toString().replaceAll("-",""));  //设置对象新的GUID
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
        boolean opResult = functionService.removeById(functionId);
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

    @RequestMapping(value = "/role/list")
    @ResponseBody
    public PageResult<Role> getRoleList(@RequestParam("page") Integer currentPage, @RequestParam("pageSize") Integer pageSize){
        Page page = new Page(currentPage,pageSize);
        List<Role> result = roleService.page(page).getRecords();
        int recordCount = roleService.count();
        return new PageResult<Role>(recordCount,result);
    }

    @ResponseBody
    @RequestMapping(value = "/role/insert",method = RequestMethod.POST)
    public PageResult<Role> insertRole(@RequestBody Role role){
        PageResult<Role> result = new PageResult<>();
        role.setId(CommonUtils.GenerateId());
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
        boolean opResult = roleService.removeById(roleId);
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

}
