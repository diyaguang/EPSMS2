package com.dygstudio.epsms.service.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dygstudio.epsms.service.entity.Function;
import com.dygstudio.epsms.service.entity.Role;
import com.dygstudio.epsms.service.entity.RoleFunctionLink;
import com.dygstudio.epsms.service.entity.User;
import com.dygstudio.epsms.service.mapper.RoleFunctionLinkMapper;
import com.dygstudio.epsms.service.mapper.RoleMapper;
import com.dygstudio.epsms.service.mapper.UserRoleLinkMapper;
import com.dygstudio.epsms.service.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈功能概述〉
 *
 * @className: RoleServiceImpl
 * @package: com.dygstudio.epsms.service.service.impl
 * @author: diyaguang
 * @date: 2020/4/26 14:18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    RoleMapper roleMapper;
    @Resource
    RoleFunctionLinkMapper roleFunctionLinkMapper;
    @Resource
    UserRoleLinkMapper userRoleLinkMapper;

    @Transactional
    public Integer deleteById(String roleId){
        Integer result = roleMapper.deleteById(roleId);
        if(result==1){
            roleFunctionLinkMapper.deleteByRoleId(roleId);
            userRoleLinkMapper.deleteByRoleId(roleId);
        }
        return result;
    }

    @Transactional
    public Integer deleteRoleList(List<Role> roleList){
        Integer resultCount = 0;
        for(Role role : roleList){
            resultCount+=deleteById(role.getId());
        }
        return resultCount;
    }

    public IPage<Role> findAllRoleForShow(Integer page, Integer pageSize){
        Page<Role> queryPage = new Page(page,pageSize);
        List<Role> result = roleMapper.findAllRoleForShow(queryPage);
        queryPage.setRecords(result);
        return queryPage;
    }
}
