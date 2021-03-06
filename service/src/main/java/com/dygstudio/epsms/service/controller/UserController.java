package com.dygstudio.epsms.service.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.interfaces.Func;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dygstudio.epsms.service.common.CommonUtils;
import com.dygstudio.epsms.service.common.PageResult;
import com.dygstudio.epsms.service.common.SysConstant;
import com.dygstudio.epsms.service.entity.Function;
import com.dygstudio.epsms.service.entity.RoleFunctionLink;
import com.dygstudio.epsms.service.entity.User;
import com.dygstudio.epsms.service.entity.UserRoleLink;
import com.dygstudio.epsms.service.service.FunctionService;
import com.dygstudio.epsms.service.service.UserRoleLinkService;
import com.dygstudio.epsms.service.service.UserService;
import com.dygstudio.epsms.service.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 〈功能概述〉
 *
 * @className: UserController
 * @package: com.dygstudio.epsms.service.controller
 * @author: diyaguang
 * @date: 2019-12-30 17:32
 */
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Resource
    UserService userService;
    @Resource
    UserRoleLinkService userRoleLinkService;
    @Resource
    FunctionService functionService;


    @ResponseBody
    @RequestMapping(value = "testError")
    public String test(){
        int a = 1/0;
        return "test";
    }

    @ResponseBody
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public User getUser(@RequestBody Map<String,String> param){
        return userService.findUserByNameAndPassword(param.get("userName"),param.get("password"));
    }

    /**
     * 功能描述: 获取用户列表，基础信息，没有表关联
     * @Param: [page, pageSize]
     * @Return: com.dygstudio.epsms.service.common.PageResult<com.dygstudio.epsms.service.entity.User>
     * @Author: diyaguang
     * @Date: 2020/5/20 15:02
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public PageResult<User> getUserList(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize){
        IPage<User> result = userService.findAllUser(page,pageSize);
        return new PageResult<User>(result.getTotal(),result.getRecords());
    }

    /**
     * 功能描述: 前端 用户列表显示，Show表示有对象关联
     * @Param: [page, pageSize]
     * @Return: com.dygstudio.epsms.service.common.PageResult<com.dygstudio.epsms.service.entity.User>
     * @Author: diyaguang
     * @Date: 2020/5/20 15:01
     */
    @ResponseBody
    @RequestMapping(value = "/listShow")
    public PageResult<User> getUserListShow(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,@RequestBody User queryUser){
        IPage<User> result = userService.findAllUserForShow(page,pageSize,queryUser);
        return new PageResult<User>(result.getTotal(),result.getRecords());
    }

    @ResponseBody
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public PageResult<User> insertUser(@RequestBody User user){
        PageResult<User> result = new PageResult<>();
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));  //设置对象新的GUID
        user.setOpDate(new Date());
        int opResult = userService.insert(user);
        if(opResult>0){
            result.setCode(SysConstant.RESULT_CODE_SUCCESSFUL);
            result.setMsg(user.getId()); //插入比较特殊，返回新对象的 ID作为成功的返回值
            result.setData(null);
            result.setCount(opResult);
        }else{
            result.setCode(SysConstant.RESULT_CODE_FAILURE);
            result.setMsg(SysConstant.RESULT_MSG_FAILURE);
            result.setData(null);
            result.setCount(opResult);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public PageResult<User> updateUser(@RequestBody User user){
        PageResult<User> result = new PageResult<>();
        user.setOpDate(new Date());
        int opResult = userService.update(user);
        if(opResult>0){
            result.setCode(SysConstant.RESULT_CODE_SUCCESSFUL);
            result.setMsg(SysConstant.RESULT_MSG_SUCCESSFUL);
            result.setData(null);
            result.setCount(opResult);
        }else{
            result.setCode(SysConstant.RESULT_CODE_FAILURE);
            result.setMsg(SysConstant.RESULT_MSG_FAILURE);
            result.setData(null);
            result.setCount(opResult);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public PageResult<User> deleteUser(@RequestParam("userId") String userId){
        PageResult<User> result = new PageResult<>();
        int opResult = userService.deleteById(userId);
        if(opResult>0){
            result.setCode(SysConstant.RESULT_CODE_SUCCESSFUL);
            result.setMsg(SysConstant.RESULT_MSG_SUCCESSFUL);
            result.setData(null);
            result.setCount(opResult);
        }else{
            result.setCode(SysConstant.RESULT_CODE_FAILURE);
            result.setMsg(SysConstant.RESULT_MSG_FAILURE);
            result.setData(null);
            result.setCount(opResult);
        }
        return result;
    }

    /**
     * 功能描述: 修改用户和角色的关联关系
     * @Param: [userId, roleIds]
     * @Return: com.dygstudio.epsms.service.common.PageResult<com.dygstudio.epsms.service.entity.UserRoleLink>
     * @Author: diyaguang
     * @Date: 2020/5/20 15:03
     */
    @ResponseBody
    @RequestMapping(value = "/userRoleLink/modify",method = RequestMethod.POST)
    public PageResult<UserRoleLink> modifyUserRoleLink(@RequestParam("userId") String userId, @RequestBody List<String> roleIds){
        boolean result = userService.modifyUserRoleLink(userId,roleIds);
        PageResult<UserRoleLink> pageResult = new PageResult<>();
        if(result) {
            pageResult.setCode(SysConstant.RESULT_CODE_SUCCESSFUL);
            pageResult.setMsg(SysConstant.RESULT_MSG_SUCCESSFUL);
            pageResult.setData(null);
            pageResult.setCount(roleIds.size());
        }else {
            pageResult.setCode(SysConstant.RESULT_CODE_FAILURE);
            pageResult.setMsg(SysConstant.RESULT_MSG_FAILURE);
            pageResult.setData(null);
            pageResult.setCount(roleIds.size());
        }
        return pageResult;
    }

    /**
     * 功能描述:获取用户与角色的关联关系（列表）
     * 没有用到，因为直接在用户管理中，返回的 用户信息中，关联了 角色列表
     * @Param: [userId]
     * @Return: com.dygstudio.epsms.service.common.PageResult<com.dygstudio.epsms.service.entity.UserRoleLink>
     * @Author: diyaguang
     * @Date: 2020/4/28 10:47
     * http://localhost:8090/api/user/userRoleLink/list?userId=7f9c095ee0ed43ba85d6f6b3d533AAAc
     */
    @ResponseBody
    @RequestMapping(value = "/userRoleLink/list")
    public PageResult<UserRoleLink> getUserRoleLinkByUserId(@RequestParam("userId") String userId){
        List<UserRoleLink> result = userRoleLinkService.getUserRoleLinkByUserId(userId);
        return new PageResult<UserRoleLink>(result.size(),result);
    }

    /**
     * 功能描述:返回指定用户的 菜单功能项。
     * 返回 Function对象列表，前端组装使用。
     * @Param: [userId]
     * @Return: com.dygstudio.epsms.service.common.PageResult<com.dygstudio.epsms.service.entity.Function>
     * @Author: diyaguang
     * @Date: 2020/4/28 14:24
     * http://localhost:8090/api/user/userMenu?userId=7f9c095ee0ed43ba85d6f6b3d533AAAc
     */
    @ResponseBody
    @RequestMapping(value = "/userMenu")
    public PageResult<Function> getMenuByUserId(@RequestParam("userId") String userId){
        List<Function> functionList = functionService.getFunctionByUserId(userId);
        List<Function> menu = functionService.buildUserMenuFunctions(functionList);
        return new PageResult<Function>(menu.size(),menu);
    }
}
