package com.knu.noticesender.core.controller;

import com.knu.noticesender.core.exception.KnuException;
import com.knu.noticesender.core.response.ApiResponse;
import com.knu.noticesender.core.response.ResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public ApiResponse<?> hello() {
        return ApiResponse.ok("hi");
    }
}
