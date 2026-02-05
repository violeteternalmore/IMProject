package org.example.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserQueryResp {
    private String username;
    private LocalDateTime createTime;
}
