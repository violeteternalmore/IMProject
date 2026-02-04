package org.example.backend.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功
    public static <T> Result<T> success(T data) {
        return new Result(200, "success", data);
    }

    // 失败
    public static <T> Result<T> error(Integer code, String message) {
        return new Result(code, message, null);
    }
}
