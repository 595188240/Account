package com.account.service;

import com.account.dto.UserDto;
import com.account.entity.User;
import com.account.repository.UserRepository;
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
import java.util.List;

/**
 * Licensed to CMIM,Inc. under the terms of the CMIM
 * Software License version 1.0.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date        Author        Version        Comments
 * 2021/4/28     ffdeng         1.0       Initial Version
 **/
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Object getAll() {
        Iterable<User> all = userRepository.findAll();
        return all;
    }

    public UnifyResponse pageUser() {
        int pageSize = 10;
        int currentPage = 0;
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(currentPage, pageSize, sort);

        PageResultModel<User> pageResultModel = new PageResultModel();
        Page<User> page = userRepository.findAll((root, query, cb) -> {
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

    public Object insertUser(User user) {
        return userRepository.save(user);
    }

    public User login(UserDto dto) {
        return userRepository.findByUserNameAndPasswordAndIsDelete(
                dto.getUserName(), dto.getPassword(), 0);
    }

}
