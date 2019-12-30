package com.dygstudio.epsms.service.service.impl;

import com.dygstudio.epsms.service.entity.User;
import com.dygstudio.epsms.service.mapper.UserMapper;
import com.dygstudio.epsms.service.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 〈功能概述〉
 *
 * @className: UserServiceImpl
 * @package: com.dygstudio.epsms.service.service.impl
 * @author: diyaguang
 * @date: 2019-12-30 17:28
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    public User findUserByNameAndPassword(String name, String password){
        return userMapper.findUserByNameAndPassword(name,password);
    }
    public User findUserById(String userId){
        return userMapper.findUserById(userId);
    }
    public User findUserByName(String name){
        return userMapper.findUserByName(name);
    }
}
