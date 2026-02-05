package org.example.backend.service;

import org.example.backend.dto.UserReq;
import org.example.backend.dto.UserQueryResp;

public interface UserService {
    Long addUser(UserReq userReq);
    UserQueryResp selectUserById(Long id);
    void updateUserById(Long id, UserReq userReq);
}
