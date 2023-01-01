package com.pcc.carrental.controller;

import com.pcc.carrental.request.CreateUserRequest;
import com.pcc.carrental.response.UserDTO;
import com.pcc.carrental.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags = "APIs for user")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "create a new user")
    @PostMapping
    ResponseEntity<String> createUser(CreateUserRequest request) {
        Long userId = userService.createUser(request);
        return ResponseEntity.ok(String.valueOf(userId));
    }

    @ApiOperation(value = "get a user's detail via its id")
    @GetMapping("/{user_id}")
    ResponseEntity<UserDTO> getUser(@PathVariable(name = "user_id") Long userId) {
        UserDTO userDTO = userService.findUser(userId);
        return ResponseEntity.ok(userDTO);
    }

}
