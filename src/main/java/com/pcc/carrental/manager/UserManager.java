package com.pcc.carrental.manager;

import com.pcc.carrental.model.User;
import com.pcc.carrental.model.mapper.UserMapper;
import com.pcc.carrental.repository.UserDAO;
import com.pcc.carrental.request.CreateUserRequest;
import com.pcc.carrental.response.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManager {

    @Autowired
    private UserDAO userDAO;

    public Long newUser(CreateUserRequest request) {
        User newUser = new User();
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setAlias(request.getAlias());
        newUser.setMobilePhoneNum(request.getMobilePhoneNum());
        newUser.setEmailAddress(request.getEmailAddress());
        userDAO.save(newUser);
        return newUser.getId();
    }

    public UserDTO findUser(Long userId) {
        return UserMapper.INSTANCE
                .toUserDTO(userDAO.findById(userId).orElse(null));
    }
}
