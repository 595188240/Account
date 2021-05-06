package com.account.controller;

import com.account.dto.UserDto;
import com.account.entity.User;
import com.account.response.UnifyResponse;
import com.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Licensed to CMIM,Inc. under the terms of the CMIM
 * Software License version 1.0.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date        Author        Version        Comments
 * 2021/4/28     ffdeng         1.0       Initial Version
 **/
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UnifyResponse login(@RequestBody UserDto dto) {
        User loginUser = userService.login(dto);
        if (Objects.nonNull(loginUser)) {
            return UnifyResponse.success(loginUser);
        }
        return UnifyResponse.failure("用户名或密码错误！");
    }

}
