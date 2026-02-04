package org.example.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserQueryRes {
    private String username;
    private LocalDateTime createTime;
}
