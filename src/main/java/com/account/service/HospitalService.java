package com.account.service;

import com.account.dto.HospitalDto;
import com.account.entity.Hospital;
import com.account.repository.HospitalRepository;
import com.account.response.UnifyResponse;
import com.account.vo.PageResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    public UnifyResponse page(Pageable pageable) {
        PageResultModel<Hospital> pageResultModel = new PageResultModel();
        Page<Hospital> page = hospitalRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList();
//            if (StringUtils.isNotBlank(custCode)) {
//                predicates.add(cb.equal(root.get("custCode"), custCode));
//            }
//            if (StringUtils.isNotBlank(custName)) {
//                predicates.add(cb.like(root.get("custName"), "%" + custName + "%"));
//            }
            predicates.add(cb.equal(root.get("isDelete"), 0));
            Predicate[] arrayPredicates = new Predicate[predicates.size()];
            return cb.and(predicates.toArray(arrayPredicates));
        }, pageable);

        if (page != null && !page.getContent().isEmpty()) {
            return UnifyResponse.success(pageResultModel.buildPageResultModel(page));
        }

        return UnifyResponse.success(pageResultModel);
    }

    public Hospital findOne(long id) {
        return hospitalRepository.getOne(id);
    }

    public Hospital save(HospitalDto dto) {
        Hospital hospital = new Hospital();
        hospital.setName(dto.getName());
        hospital.setAddress(dto.getAddress());
        hospital.setPhone(dto.getPhone());
        return hospitalRepository.save(hospital);
    }

    public Hospital edit(HospitalDto dto) {
        Hospital hospital = hospitalRepository.getOne(dto.getId());
        hospital.setName(dto.getName());
        hospital.setAddress(dto.getAddress());
        hospital.setPhone(dto.getPhone());
        hospital.setModifyTime(new Date());
        return hospitalRepository.save(hospital);
    }

    public void delete(long id) {
        Hospital one = findOne(id);
        one.setIsDelete(1);
        one.setModifyTime(new Date());
        hospitalRepository.save(one);
    }

}
