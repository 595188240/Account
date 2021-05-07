package com.account.service;

import com.account.dto.OutDto;
import com.account.entity.Out;
import com.account.repository.OutRepository;
import com.account.response.UnifyResponse;
import com.account.vo.PageResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class OutService {

    @Autowired
    private OutRepository outRepository;

    public UnifyResponse page() {
        int pageSize = 10;
        int currentPage = 0;
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(currentPage, pageSize, sort);

        PageResultModel<Out> pageResultModel = new PageResultModel();
        Page<Out> page = outRepository.findAll((root, query, cb) -> {
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

    public void delete(long id) {
        Out one = getOne(id);
        one.setModifyTime(new Date());
        one.setIsDelete(1);
        outRepository.save(one);
    }
}
