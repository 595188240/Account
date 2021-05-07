package com.account.dto;

import javax.validation.constraints.NotBlank;

/**
 * Licensed to CMIM,Inc. under the terms of the CMIM
 * Software License version 1.0.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date        Author        Version        Comments
 * 2021/5/7     ffdeng         1.0       Initial Version
 **/
public class IdDto {

    @NotBlank(message = "id不能为空")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
