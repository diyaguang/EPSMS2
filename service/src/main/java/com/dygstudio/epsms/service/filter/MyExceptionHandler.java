package com.dygstudio.epsms.service.filter;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈功能概述〉
 *
 * @className: MyExceptionHandler
 * @package: com.dygstudio.epsms.service.filter
 * @author: diyaguang
 * @date: 2020/3/31 11:02 上午
 */
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public String ErrorHandler(Exception e){
        if(e.getClass() == AuthorizationException.class)
            return "没有通过权限认证!";
        else
            return "bad"+e.getMessage();
    }
}
