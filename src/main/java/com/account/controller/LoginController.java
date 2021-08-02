package com.account.controller;

import com.account.dto.UserDto;
import com.account.entity.User;
import com.account.response.UnifyResponse;
import com.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
@ControllerAdvice
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UnifyResponse login(@Valid @RequestBody UserDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return validError(result);
        }
        User loginUser = userService.login(dto);
        if (Objects.nonNull(loginUser)) {
            return UnifyResponse.success(loginUser);
        }
        return UnifyResponse.failure("用户名或密码错误！");
    }

    private UnifyResponse validError(BindingResult result) {
        List<ObjectError> allErrors = result.getAllErrors();
        ObjectError objectError = allErrors.get(0);
        String defaultMessage = objectError.getDefaultMessage();
        return UnifyResponse.failure(defaultMessage);
    }

}
