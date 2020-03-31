package com.dygstudio.epsms.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.dygstudio.epsms.service.entity.User;
import com.dygstudio.epsms.service.service.UserService;
import org.apache.ibatis.executor.ResultExtractor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈功能概述〉
 *
 * @className: LoginController
 * @package: com.dygstudio.epsms.service.controller
 * @author: diyaguang
 * @date: 2020/3/31 2:19 下午
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public JSONObject login(User user){
        JSONObject result = new JSONObject();

        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getName(),
                user.getPassword()
        );

        try {
            subject.login(usernamePasswordToken);
            User u = userService.findUserByName(user.getName());


        }catch(AuthenticationException e){
            e.printStackTrace();
            result.put("msg","账号或密码错误");
        }catch (AuthorizationException e){
            e.printStackTrace();
            result.put("msg","没有权限");
        }

        return result;
    }

    @RequiresRoles("admin")
    @RequiresPermissions("add")
    @RequestMapping("/index")
    public String index(){
        return "index!";
    }
}
