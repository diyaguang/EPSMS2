package com.dygstudio.epsms.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dygstudio.epsms.service.entity.Role;
import com.dygstudio.epsms.service.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper extends BaseMapper {
    User findUserByNameAndPassword(String name, String password);
    User findUserById(String userId);
    User findUserByName(String name);
    List<User> findAllUser(HashMap<String,Object> queryItem);
    String countUser();
    List<User> findUserByObject(HashMap<String,Object> queryItem);
    int insert(User user);
    int insertList(List<User> userList);
    int update(User user);
    int deleteById(String userId);
}
