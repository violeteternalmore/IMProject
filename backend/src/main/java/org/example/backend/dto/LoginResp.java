package org.example.backend.dto;

import lombok.Data;

@Data
public class LoginResp {
    private String token;
    private String username;
}
