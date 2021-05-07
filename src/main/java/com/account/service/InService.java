package com.account.service;

import com.account.dto.InDto;
import com.account.entity.InAccount;
import com.account.repository.InRepository;
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
public class InService {

    @Autowired
    private InRepository inRepository;

    public UnifyResponse page() {
        int pageSize = 10;
        int currentPage = 0;
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(currentPage, pageSize, sort);

        PageResultModel<InAccount> pageResultModel = new PageResultModel();
        Page<InAccount> page = inRepository.findAll((root, query, cb) -> {
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

    public InAccount getOne(long id) {
        return inRepository.getOne(id);
    }

    public InAccount save(InDto dto) {
        InAccount in = new InAccount();
        in.setHospitalId(dto.getHospitalId());
        in.setMoney(dto.getMoney());
        in.setInDesc(dto.getRemark());
        return inRepository.save(in);
    }

    public InAccount edit(InDto dto) {
        InAccount in = getOne(dto.getId());
        in.setHospitalId(dto.getHospitalId());
        in.setMoney(dto.getMoney());
        in.setInDesc(dto.getRemark());
        return inRepository.save(in);
    }

    public void delete(long id) {
        InAccount one = getOne(id);
        one.setModifyTime(new Date());
        one.setIsDelete(1);
        inRepository.save(one);
    }
}
