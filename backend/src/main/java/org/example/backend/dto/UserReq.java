package org.example.backend.dto;

import lombok.Data;
import org.example.backend.validation.Create;
import org.example.backend.validation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserReq {
    @NotBlank(groups = {Create.class, Update.class}, message = "用户名不能为空")
    @Size(min = 2, max = 20, message = "用户名长度需为2-20字符")
    private String username;
    @NotBlank(groups = {Create.class, Update.class}, message = "密码不能为空")
    @Size(min = 8, max = 16, message = "密码长度需为8-16字符")
    private String password;

}
