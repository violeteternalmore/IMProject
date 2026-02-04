package org.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("User")
public class User {

    private Long id;
    private String username;
    private String password;
    private LocalDateTime createTime;
}
