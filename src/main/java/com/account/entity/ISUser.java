package com.account.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * @author ffdeng2
 * @date 2022-3-8 10:40
 */
public interface ISUser {
    String getUserName();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime getCreateTime();
}
