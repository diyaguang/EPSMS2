package com.dygstudio.epsms.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dygstudio.epsms.service.entity.User;
import com.dygstudio.epsms.service.vo.MenuVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface UserService extends IService<User> {
    User findUserByNameAndPassword(String name, String password);
    User findUserById(String userId);
    User findUserByName(String name);
    IPage<User> findAllUser(Integer page, Integer pageSize);
    Integer countUser();
    IPage<User> findUserByObject(User queryItem,Integer page,Integer pageSize);
    Integer insert(User user);
    Integer insertList(List<User> userList);
    Integer update(User user);
    Integer deleteById(String userId);
    Integer deleteUserList(List<User> userList);
    boolean modifyUserRoleLink(String userId, List<String> roleIds);
}
