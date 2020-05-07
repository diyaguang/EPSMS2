package com.dygstudio.epsms.service.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dygstudio.epsms.service.common.CommonUtils;
import com.dygstudio.epsms.service.entity.User;
import com.dygstudio.epsms.service.entity.UserRoleLink;
import com.dygstudio.epsms.service.mapper.UserMapper;
import com.dygstudio.epsms.service.service.UserRoleLinkService;
import com.dygstudio.epsms.service.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    UserRoleLinkService userRoleLinkService;

    public User findUserByNameAndPassword(String name, String password){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("Name",name);
        queryWrapper.eq("Password",password);
        return userMapper.selectOne(queryWrapper);
    }
    public User findUserById(String userId){
        return userMapper.selectById(userId);
    }
    public User findUserByName(String name){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("Name",name);
        return userMapper.selectOne(queryWrapper);
    }
    public IPage<User> findAllUser(Integer page,Integer pageSize){
        IPage queryPage = new Page(page,pageSize);
        return userMapper.selectPage(queryPage,new QueryWrapper<User>());
    }
    public IPage<User> findAllUserForShow(Integer page, Integer pageSize){
        Page<User> queryPage = new Page(page,pageSize);
        List<User> result = userMapper.findAllUserForShow(queryPage);
        queryPage.setRecords(result);
        return queryPage;
    }
    public Integer countUser(){
        return userMapper.selectCount(new QueryWrapper<User>());
    }
    public IPage<User>  findUserByObject(User queryItem,Integer page,Integer pageSize){
        IPage queryPage = new Page(page,pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(!StrUtil.isBlank(queryItem.getName())){
            queryWrapper.eq("Name",queryItem.getName());
        }
        return userMapper.selectPage(queryPage,queryWrapper);
    }
    public Integer insert(User user){
        return userMapper.insert(user);
    }

    @Transactional
    public Integer insertList(List<User> userList){
        Integer resultCount = 0;
        for(User user : userList){
            resultCount+=insert(user);
        }
        return resultCount;
    }

    @Transactional
    public Integer deleteUserList(List<User> userList){
        Integer resultCount = 0;
        for(User user : userList){
            resultCount+=deleteById(user.getId());
        }
        return resultCount;
    }
    public Integer update(User user){
        return userMapper.updateById(user);
    }

    @Transactional
    public Integer deleteById(String userId){
        Integer result = userMapper.deleteById(userId);
        if(result==1){
            userRoleLinkService.deleteByUserId(userId);
        }
        return result;
    }
    public boolean modifyUserRoleLink(String userId, List<String> roleIds){
        //先删除原有关联记录
        userRoleLinkService.deleteByUserId(userId);
        //插入新的关联关系
        if(roleIds.size()>0){
            UserRoleLink link  = new UserRoleLink();
            for(String roleId : roleIds){
                link.setId(CommonUtils.GenerateId());
                link.setUserId(userId);
                link.setRoleId(roleId);
                userRoleLinkService.save(link);
            }
        }
        return true;
    }
}
