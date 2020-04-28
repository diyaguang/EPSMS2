package com.dygstudio.epsms.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dygstudio.epsms.service.entity.UserRoleLink;
import com.dygstudio.epsms.service.mapper.UserRoleLinkMapper;
import com.dygstudio.epsms.service.service.UserRoleLinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈功能概述〉
 *
 * @className: UserRoleLinkServiceImpl
 * @package: com.dygstudio.epsms.service.service.impl
 * @author: diyaguang
 * @date: 2020/4/26 9:49
 */
@Service
public class UserRoleLinkServiceImpl extends ServiceImpl<UserRoleLinkMapper, UserRoleLink> implements UserRoleLinkService {

    @Resource
    UserRoleLinkMapper userRoleLinkMapper;

    public int  deleteByUserId(String userId){
        return userRoleLinkMapper.deleteByUserId(userId);
    }
    public int deleteByRoleId(String roleId){
        return userRoleLinkMapper.deleteByRoleId(roleId);
    }
    public boolean addUserRoleLink(List<UserRoleLink> links){
        int opResultCount = 0;
        for(UserRoleLink link : links){
            opResultCount+= userRoleLinkMapper.insert(link);
        }
        if(opResultCount==links.size())
            return true;
        return  false;
    }
    public List<UserRoleLink> getUserRoleLinkByUserId(String userId){
        return userRoleLinkMapper.findRoleByUserId(userId);
    }
}
