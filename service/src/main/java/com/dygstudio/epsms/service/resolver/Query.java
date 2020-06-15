package com.dygstudio.epsms.service.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.dygstudio.epsms.service.entity.User;
import com.dygstudio.epsms.service.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈功能概述〉
 *
 * @className: Query
 * @package: com.dygstudio.epsms.service.resolver
 * @author: diyaguang
 * @date: 2020/6/15 11:28
 */
@Component
public class Query implements GraphQLQueryResolver {

    @Resource
    private UserService userService;

    public List<User> findAllUser(){
        return userService.list();
    }
    public Integer countUsers(){
        return userService.countUser();
    }
    public User findOneUser(String id){
        return userService.findUserById(id);
    }
}
