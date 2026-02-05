package org.example.backend.service;

import org.example.backend.dto.LoginReq;
import org.example.backend.dto.LoginResp;

public interface AuthService {
    LoginResp login(LoginReq loginReq);
}
