package com.account.service;

import com.account.dto.OutDto;
import com.account.entity.Out;
import com.account.repository.OutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Licensed to CMIM,Inc. under the terms of the CMIM
 * Software License version 1.0.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date        Author        Version        Comments
 * 2021/5/6     ffdeng         1.0       Initial Version
 **/
@Service
public class OutService {

    @Autowired
    private OutRepository outRepository;

    public Out save(OutDto dto) {
        Out out = new Out();
        out.setHospitalId(dto.getHospitalId());
        out.setMoney(dto.getMoney());
        out.setRemark(dto.getRemark());
        return outRepository.save(out);
    }

    public Out getOne(long id) {
        return outRepository.findById(id).get();
    }

    public Out edit(OutDto dto) {
        Out out = getOne(dto.getId());
        out.setHospitalId(dto.getHospitalId());
        out.setMoney(dto.getMoney());
        out.setRemark(dto.getRemark());
        out.setModifyTime(new Date());
        return outRepository.save(out);
    }

}
