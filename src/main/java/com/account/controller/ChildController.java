package com.account.controller;

import com.account.response.UnifyResponse;
import com.account.service.ChildService;
import com.account.service.ClassService;
import com.account.service.SchoolService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ffdeng2
 * 关联查询Demo
 */
@RestController
@RequestMapping("/child")
public class ChildController {

    @Resource
    private SchoolService schoolService;

    @Resource
    private ClassService classService;

    @Resource
    private ChildService childService;

    @GetMapping("/getAllSchool")
    public UnifyResponse getAllSchool() {
        return UnifyResponse.success(schoolService.getAll());
    }

    @GetMapping("/pageSchool")
    public UnifyResponse pageSchool(@PageableDefault Pageable pageable) {
        return UnifyResponse.success(schoolService.page(pageable));
    }

    @GetMapping("/getAllClass")
    public UnifyResponse getAllClass() {
        return UnifyResponse.success(classService.getAll());
    }

    @GetMapping("/getAllChild")
    public UnifyResponse getAllChild() {
        return UnifyResponse.success(childService.getAll());
    }

    /**
     * 自定义返回体
     * @return
     */
    @GetMapping("/getAllChild/1")
    public UnifyResponse getAllChild1() {
        return UnifyResponse.success(childService.findAllChild());
    }
}
