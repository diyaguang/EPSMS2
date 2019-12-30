package com.dygstudio.epsms.service.controller;

import com.dygstudio.epsms.service.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 〈功能概述〉
 *
 * @className: UserController
 * @package: com.dygstudio.epsms.service.controller
 * @author: diyaguang
 * @date: 2019-12-30 17:32
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Resource
    UserService userService;

    @ResponseBody
    public String getUserList(){
        //return userService.
        return "ok";
    }
}
