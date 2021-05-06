package com.account.repository;

import com.account.entity.In;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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
public interface InRepository extends CrudRepository<In, Long> {

}
