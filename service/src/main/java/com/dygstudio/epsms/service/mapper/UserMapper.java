package com.dygstudio.epsms.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dygstudio.epsms.service.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper {
    User findUserByNameAndPassword(String name, String password);
    User findUserById(String userId);
    User findUserByName(String name);
}
