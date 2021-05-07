package com.account.controller;

import com.account.service.InService;
import com.account.service.OutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private InService inService;
    @Autowired
    private OutService outService;

//    @PostMapping("/account")
//    public UnifyResponse account() {
//
//    }

}
