package com.dygstudio.epsms.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dygstudio.epsms.service.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper {
    User findUserByNameAndPassword(String name, String password);
    User findUserById(String userId);
    User findUserByName(String name);
    List<User> findAllUser();
}
