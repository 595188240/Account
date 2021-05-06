package com.account.response;

/**
 * Licensed to CMIM,Inc. under the terms of the CMIM
 * Software License version 1.0.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date        Author        Version        Comments
 * 2021/4/29     ffdeng         1.0       Initial Version
 **/
public class UnifyResponse<T> {
    private String code;
    private String message;
    private T result;

    public static UnifyResponse success() {
        UnifyResponse unifyResponse = new UnifyResponse();
        unifyResponse.setCode("200");
        unifyResponse.setMessage("请求成功");
        return unifyResponse;
    }

    public static <K> UnifyResponse<K> success(K t) {
        UnifyResponse unifyResponse = success();
        unifyResponse.setResult(t);
        return unifyResponse;
    }

    public static UnifyResponse failure() {
        UnifyResponse unifyResponse = new UnifyResponse();
        unifyResponse.setCode("500");
        unifyResponse.setMessage("请求失败");
        return unifyResponse;
    }

    public static UnifyResponse failure(String message) {
        UnifyResponse unifyResponse = new UnifyResponse();
        unifyResponse.setCode("500");
        unifyResponse.setMessage(message);
        return unifyResponse;
    }

    public static UnifyResponse failure(String code, String message) {
        UnifyResponse unifyResponse = new UnifyResponse();
        unifyResponse.setCode(code);
        unifyResponse.setMessage(message);
        return unifyResponse;
    }

    public UnifyResponse() {
    }

    public UnifyResponse(String code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
