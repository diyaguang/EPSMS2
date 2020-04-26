package com.dygstudio.epsms.service.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dygstudio.epsms.service.common.PageResult;
import com.dygstudio.epsms.service.entity.Function;
import com.dygstudio.epsms.service.entity.Role;
import com.dygstudio.epsms.service.entity.User;
import com.dygstudio.epsms.service.service.FunctionService;
import com.dygstudio.epsms.service.service.RoleService;
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
            result.setCode(0);
            result.setMsg(function.getId()); //插入比较特殊，返回新对象的 ID作为成功的返回值
            result.setData(null);
            result.setCount(1);
        }else{
            result.setCode(1);
            result.setMsg("insert failure");
            result.setData(null);
            result.setCount(0);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/function/update",method = RequestMethod.POST)
    public PageResult<Function> updateFunction(@RequestBody Function function){
        PageResult<Function> result = new PageResult<>();
        boolean opResult = functionService.saveOrUpdate(function);
        if(opResult){
            result.setCode(0);
            result.setMsg("ok");
            result.setData(null);
            result.setCount(1);
        }else{
            result.setCode(1);
            result.setMsg("update failure");
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
            result.setCode(0);
            result.setMsg("ok");
            result.setData(null);
            result.setCount(1);
        }else{
            result.setCode(1);
            result.setMsg("delete failure");
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
}
