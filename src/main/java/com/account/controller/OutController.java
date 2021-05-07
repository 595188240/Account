package com.account.controller;

import com.account.dto.IdDto;
import com.account.dto.OutDto;
import com.account.response.UnifyResponse;
import com.account.service.OutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Licensed to CMIM,Inc. under the terms of the CMIM
 * Software License version 1.0.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date        Author        Version        Comments
 * 2021/5/6     ffdeng         1.0       Initial Version
 **/
@RestController
@RequestMapping("/out")
public class OutController {

    @Autowired
    private OutService outService;

    @PostMapping("/page")
    public UnifyResponse page() {
        return outService.page();
    }

    @PostMapping("/save")
    public UnifyResponse save(@RequestBody OutDto dto) {
        return UnifyResponse.success(outService.save(dto));
    }

    @PostMapping("/getOne")
    public UnifyResponse getOne(@RequestBody IdDto dto) {
        return UnifyResponse.success(outService.getOne(dto.getId()));
    }

    @PostMapping("/edit")
    public UnifyResponse edit(@RequestBody OutDto dto) {
        return UnifyResponse.success(outService.edit(dto));
    }

    @PostMapping("/delete")
    public UnifyResponse delete(@RequestBody IdDto dto) {
        outService.delete(dto.getId());
        return UnifyResponse.success();
    }

}
