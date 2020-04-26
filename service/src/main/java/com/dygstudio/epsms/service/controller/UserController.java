package com.dygstudio.epsms.service.controller;

import com.baomidou.mybatisplus.core.conditions.interfaces.Func;
import com.dygstudio.epsms.service.common.PageResult;
import com.dygstudio.epsms.service.entity.Function;
import com.dygstudio.epsms.service.entity.User;
import com.dygstudio.epsms.service.service.FunctionService;
import com.dygstudio.epsms.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

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

    @Autowired
    UserService userService;
    @Autowired
    FunctionService functionService;

    @ResponseBody
    @RequestMapping(value = "testError")
    public String test(){
        int a = 1/0;
        return "test";
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public PageResult<User> getUserList(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize){
        List<User> result = userService.findAllUser(page,pageSize);
        Integer countUserSize = Integer.parseInt(userService.countUser());
        return new PageResult<User>(countUserSize,result);
    }

    @ResponseBody
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public PageResult<User> insertUser(@RequestBody User user){
        PageResult<User> result = new PageResult<>();
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));  //设置对象新的GUID
        int opResult = userService.insert(user);
        if(opResult>0){
            result.setCode(0);
            result.setMsg(user.getId()); //插入比较特殊，返回新对象的 ID作为成功的返回值
            result.setData(null);
            result.setCount(opResult);
        }else{
            result.setCode(1);
            result.setMsg("insert failure");
            result.setData(null);
            result.setCount(opResult);
        }
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public PageResult<User> updateUser(@RequestBody User user){
        PageResult<User> result = new PageResult<>();
        int opResult = userService.update(user);
        if(opResult>0){
            result.setCode(0);
            result.setMsg("ok");
            result.setData(null);
            result.setCount(opResult);
        }else{
            result.setCode(1);
            result.setMsg("update failure");
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
            result.setCode(0);
            result.setMsg("ok");
            result.setData(null);
            result.setCount(opResult);
        }else{
            result.setCode(1);
            result.setMsg("delete failure");
            result.setData(null);
            result.setCount(opResult);
        }
        return result;
    }
}
