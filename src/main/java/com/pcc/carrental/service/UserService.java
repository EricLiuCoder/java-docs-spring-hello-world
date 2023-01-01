package com.pcc.carrental.service;

import com.pcc.carrental.request.CreateUserRequest;
import com.pcc.carrental.response.UserDTO;

public interface UserService {
    Long createUser(CreateUserRequest request);

    UserDTO findUser(Long userId);
}
