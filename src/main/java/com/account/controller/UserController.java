package com.account.controller;

import com.account.dto.UserDto;
import com.account.entity.User;
import com.account.response.UnifyResponse;
import com.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Licensed to CMIM,Inc. under the terms of the CMIM
 * Software License version 1.0.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date        Author        Version        Comments
 * 2021/4/29     ffdeng         1.0       Initial Version
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getAllUser")
    public Object getAllUser() {
        return userService.getAll();
    }

    @PostMapping("/insertUser")
    public Object insertUser(@RequestBody UserDto dto) {
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        return userService.insertUser(user);
    }

    @PostMapping("/pageUser")
    public UnifyResponse pageUser() {
        return userService.pageUser();
    }

    @GetMapping("/pageUser")
    public UnifyResponse getPageUser() {
        return userService.getPageUser();
    }
}
