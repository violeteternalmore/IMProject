package org.example.backend.controller;

import org.example.backend.common.Result;
import org.example.backend.dto.UserReq;
import org.example.backend.dto.UserQueryResp;
import org.example.backend.service.UserService;
import org.example.backend.validation.Create;
import org.example.backend.validation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    // 添加用户
    @PostMapping("/users")
    public Result<Long> addUser(@Validated(Create.class) @RequestBody UserReq userReq) {
        return Result.success(userService.addUser(userReq));
    }

    // 查询用户（使用id）
    @GetMapping("/users/{id}")
    public Result<UserQueryResp> selectUserById(@PathVariable Long id) {
        return Result.success(userService.selectUserById(id));
    }

    // 更新用户
    @PutMapping("/users/{id}")
    public Result<Void> updateUserById(
            @PathVariable Long id, @Validated(Update.class) @RequestBody UserReq userReq) {
        userService.updateUserById(id, userReq);
        return Result.success(null);
    }
}
