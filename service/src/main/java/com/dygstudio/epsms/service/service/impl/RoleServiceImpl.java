package com.dygstudio.epsms.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dygstudio.epsms.service.entity.Role;
import com.dygstudio.epsms.service.mapper.RoleMapper;
import com.dygstudio.epsms.service.service.RoleService;
import org.springframework.stereotype.Service;

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
}
