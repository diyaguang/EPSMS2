package com.dygstudio.epsms.service.controller;

import com.dygstudio.epsms.service.entity.User;
import com.dygstudio.epsms.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @ResponseBody
    @RequestMapping(value = "/list")
    public List<User> getUserList(){
        List<User> result = userService.findAllUser();
        return result;
    }
}
