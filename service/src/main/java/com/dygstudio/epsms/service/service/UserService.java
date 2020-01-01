package com.dygstudio.epsms.service.service;

import com.dygstudio.epsms.service.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findUserByNameAndPassword(String name, String password);
    User findUserById(String userId);
    User findUserByName(String name);
    List<User> findAllUser();
}
