package com.pcc.carrental.service;

import com.pcc.carrental.manager.UserManager;
import com.pcc.carrental.request.CreateUserRequest;
import com.pcc.carrental.response.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserManager userManager;

    @Override
    public Long createUser(CreateUserRequest request) {
        return userManager.newUser(request);
    }

    @Override
    public UserDTO findUser(Long userId) {
        return userManager.findUser(userId);
    }
}
