package org.example.backend.service;

import org.example.backend.common.Result;
import org.example.backend.dto.UserCreateReq;
import org.example.backend.dto.UserQueryRes;
import org.example.backend.entity.User;

import java.util.List;

public interface UserService {
    Long addUser(UserCreateReq userReq);
    UserQueryRes selectUserById(Long id);
}
