package org.example.backend.controller;

import org.example.backend.common.Result;
import org.example.backend.dto.UserCreateReq;
import org.example.backend.dto.UserQueryRes;
import org.example.backend.entity.User;
import org.example.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    // 添加用户
    @PostMapping("/users")
    public Result<Long> addUser(@Valid @RequestBody UserCreateReq userReq) {
        return Result.success(userService.addUser(userReq));
    }

    // 查询用户（使用id）
    @GetMapping("/users/{id}")
    public Result<UserQueryRes> selectUserById(@PathVariable Long id) {
        return Result.success(userService.selectUserById(id));
    }

}
