package com.account.controller;

import com.account.dto.HospitalDto;
import com.account.response.UnifyResponse;
import com.account.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/page")
    public UnifyResponse page() {
        int pageSize = 10;
        int currentPage = 0;
        Sort sort = Sort.by(Sort.Direction.DESC, "create_time");
        Pageable pageable = PageRequest.of(currentPage, pageSize, sort);

        return hospitalService.page(pageable);
    }

    @PostMapping("/findOne")
    public UnifyResponse findOne(@RequestBody HospitalDto dto) {
        return UnifyResponse.success(hospitalService.findOne(dto.getId()));
    }

    @PostMapping("/save")
    public UnifyResponse save(@RequestBody HospitalDto dto) {
         return UnifyResponse.success(hospitalService.save(dto));
    }

    @PostMapping("/edit")
    public UnifyResponse edit(@RequestBody HospitalDto dto) {
        return UnifyResponse.success(hospitalService.edit(dto));
    }

    @PostMapping("/delete")
    public UnifyResponse delete(@RequestBody HospitalDto dto) {
        hospitalService.delete(dto.getId());
        return UnifyResponse.success();
    }

}
