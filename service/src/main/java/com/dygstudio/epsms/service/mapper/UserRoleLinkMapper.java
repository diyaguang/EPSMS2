package com.dygstudio.epsms.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dygstudio.epsms.service.entity.UserRoleLink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 〈功能概述〉
 *
 * @className: UserRoleLinkMapper
 * @package: com.dygstudio.epsms.service.mapper
 * @author: diyaguang
 * @date: 2020/4/25 15:32
 */
@Mapper
public interface UserRoleLinkMapper  extends BaseMapper<UserRoleLink> {
    int  deleteByUserId(String userId);
    int deleteByRoleId(String roleId);
    List<UserRoleLink> findRoleByUserId(String userId);
}
