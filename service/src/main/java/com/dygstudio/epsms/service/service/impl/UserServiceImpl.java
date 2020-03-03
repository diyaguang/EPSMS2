package com.dygstudio.epsms.service.service.impl;

import com.dygstudio.epsms.service.entity.User;
import com.dygstudio.epsms.service.mapper.UserMapper;
import com.dygstudio.epsms.service.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

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
    public List<User> findAllUser(Integer page,Integer pageSize){
        HashMap<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageStart",pageSize*(page-1));
        queryMap.put("pageSize",pageSize);
        return userMapper.findAllUser(queryMap);
    }
    public String countUser(){
        return userMapper.countUser();
    }
    public List<User> findUserByObject(User queryItem,Integer page,Integer pageSize){
        HashMap<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageStart",pageSize*(page-1));
        queryMap.put("pageSize",pageSize);
        queryMap.put("User",queryItem);
        return userMapper.findUserByObject(queryMap);
    }
    public int insert(User user){
        return userMapper.insert(user);
    }
    public int insertList(List<User> userList){
        return userMapper.insertList(userList);
    }
    public int update(User user){
        return userMapper.update(user);
    }
    public int deleteById(String userId){
        return userMapper.deleteById(userId);
    }
}
