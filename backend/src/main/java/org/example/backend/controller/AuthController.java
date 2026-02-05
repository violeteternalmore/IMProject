package org.example.backend.controller;

import org.example.backend.common.Result;
import org.example.backend.dto.LoginReq;
import org.example.backend.dto.LoginResp;
import org.example.backend.service.AuthService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class AuthController {

    @Resource
    private AuthService authService;

    @PutMapping("/login")
    public Result<LoginResp> login(@Valid @RequestBody LoginReq loginReq) {
        return Result.success(authService.login(loginReq));
    }
}
