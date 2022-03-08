package com.account.repository;

import com.account.entity.ISUser;
import com.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

/**
 * Licensed to CMIM,Inc. under the terms of the CMIM
 * Software License version 1.0.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date        Author        Version        Comments
 * 2021/4/28     ffdeng         1.0       Initial Version
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUserNameAndPasswordAndIsDelete(String userName, String password, Integer isDelete);

    /**
     * 基于接口自定义返回值
     */
    @Query(nativeQuery = true, value = "select user_name as userName, create_time as CreateTime from user")
    Stream<ISUser> findUser();

}
