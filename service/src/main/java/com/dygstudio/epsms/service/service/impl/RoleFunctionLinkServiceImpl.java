package com.dygstudio.epsms.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dygstudio.epsms.service.entity.RoleFunctionLink;
import com.dygstudio.epsms.service.mapper.RoleFunctionLinkMapper;
import com.dygstudio.epsms.service.service.RoleFunctionLinkService;

import javax.annotation.Resource;

/**
 * 〈功能概述〉
 *
 * @className: RoleFunctionLinkServiceImpl
 * @package: com.dygstudio.epsms.service.service.impl
 * @author: diyaguang
 * @date: 2020/4/26 10:21
 */
public class RoleFunctionLinkServiceImpl extends ServiceImpl<RoleFunctionLinkMapper, RoleFunctionLink> implements RoleFunctionLinkService {
    @Resource
    RoleFunctionLinkMapper roleFunctionLinkMapper;

    public int  deleteByRoleId(String roleId){
        return roleFunctionLinkMapper.deleteByRoleId(roleId);
    }
    public int deleteByFuncId(String funcId){
        return roleFunctionLinkMapper.deleteByFuncId(funcId);
    }
}
