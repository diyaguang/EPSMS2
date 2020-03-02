package com.dygstudio.epsms.service.service;

import com.dygstudio.epsms.service.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface UserService {
    User findUserByNameAndPassword(String name, String password);
    User findUserById(String userId);
    User findUserByName(String name);
    List<User> findAllUser(Integer page,Integer pageSize);
    String countUser();
    List<User> findUserByObject(User queryItem,Integer page,Integer pageSize);
}
