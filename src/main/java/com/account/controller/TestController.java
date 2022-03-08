package com.account.controller;

import com.account.annotations.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ffdeng2
 * @date 2022-3-4 17:31
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Test(value = "t1")
    @GetMapping("/t1")
    public String t1(@RequestParam String str, @RequestParam String s) {
        return str;
    }

}
