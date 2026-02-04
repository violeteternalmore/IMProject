package org.example.backend.controller;

import org.example.backend.entity.User;
import org.example.backend.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/users")
    public List<User> listUsers() {
        return userMapper.selectList(null);
    }
}
