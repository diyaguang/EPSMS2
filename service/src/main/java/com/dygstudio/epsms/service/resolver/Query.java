package com.dygstudio.epsms.service.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.dygstudio.epsms.service.entity.Client;
import com.dygstudio.epsms.service.entity.DictInfo;
import com.dygstudio.epsms.service.entity.User;
import com.dygstudio.epsms.service.service.DictInfoService;
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

    @Resource
    private DictInfoService dictInfoService;

    public List<User> findAllUser(){
        return userService.list();
    }
    public Integer countUsers(){
        return userService.countUser();
    }
    public User findOneUser(String id){
        return userService.findUserById(id);
    }


    public Client client(String id){
        return null;
    }

    public DictInfo findDictInfo(String id){
        return dictInfoService.findObjectAndChildById(id);
    }
    public List<DictInfo> findAllDictInfo(){
        return dictInfoService.findByTopObject();
    }
}
