package com.account.controller;

import com.account.dto.IdDto;
import com.account.dto.InDto;
import com.account.response.UnifyResponse;
import com.account.service.InService;
import com.account.util.DataEntity;
import com.account.util.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Licensed to CMIM,Inc. under the terms of the CMIM
 * Software License version 1.0.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date        Author        Version        Comments
 * 2021/5/6     ffdeng         1.0       Initial Version
 **/
@RestController
@RequestMapping("/inAccount")
@ControllerAdvice
public class InAccountController {

    @Autowired
    private InService inService;

    @PostMapping("/page")
    public UnifyResponse page() {
        return inService.page();
    }

    @PostMapping("/save")
    public UnifyResponse save(@RequestBody InDto dto) {
        return UnifyResponse.success(inService.save(dto));
    }

    @PostMapping("/getOne")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public UnifyResponse getOne(@Valid @RequestBody IdDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return validError(result);
        }
        return UnifyResponse.success(inService.getOne(dto.getId()));
    }

    private UnifyResponse validError(BindingResult result) {
        List<ObjectError> allErrors = result.getAllErrors();
        ObjectError objectError = allErrors.get(0);
        String defaultMessage = objectError.getDefaultMessage();
        return UnifyResponse.failure(defaultMessage);
    }

    @PostMapping("/edit")
    public UnifyResponse edit(@RequestBody InDto dto) {
        return UnifyResponse.success(inService.edit(dto));
    }

    @PostMapping("/delete")
    public UnifyResponse delete(@RequestBody IdDto dto) {
        inService.delete(dto.getId());
        return UnifyResponse.success();
    }

    @RequestMapping("/excel")
    public void excel(HttpServletResponse response, HttpServletRequest request) {
        List<DataEntity> dataList = null;
        try {
            dataList = getDate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String fileName;
        String[] title;
        String[] field;

        fileName = System.currentTimeMillis() + ".xlsx";
        title = new String[]{"日期", "姓名", "工号", "校区", "部门", "在校记录", "第一次记录时间",
                "最后一次记录时间", "请假时长(小时)"};
        field = new String[]{"statDate", "username", "workNo", "campus", "department", "schoolRecordNum",
                "recFirstTime", "recLastTime", "leaveHour"};

        try {
            ExcelUtil.exportExcel(dataList, fileName, title, field, request, response);
        }
        catch (UnsupportedEncodingException e) {
        }
    }

    private List<DataEntity> getDate() throws Exception {
        HttpPost post = new HttpPost("http://192.168.8.59:8002" + "/ecs-server/services/svWater/findWaterDeviceinfoList");
        post.setHeader("Content-Type", "application/json");

        // 获取链接对象
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = client.execute(post);
        HttpEntity resEntity = response.getEntity();
        System.out.println("请求返回状态值：" + response.getStatusLine().getStatusCode());

        String resultStr = EntityUtils.toString(resEntity, "utf-8");
        System.out.println("返回数据Str：" + resultStr);

        Map jsonMap = JSON.parseObject(resultStr, Map.class);
        System.out.println(jsonMap);
        JSONArray jsonArr = JSON.parseArray(String.valueOf(jsonMap.get("dataResults")));
        System.out.println(("jsonArr：" + jsonArr.toJSONString()));

        return JSONArray.parseArray(String.valueOf(jsonMap.get("dataResults")), DataEntity.class);
    }
}
