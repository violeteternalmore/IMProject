package org.example.backend.exception;

import org.example.backend.common.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<Void> businessExceptionHandler(BusinessException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<Void> MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        String name = e.getName();
        Object value = e.getValue();
        Class<?> requiredType = e.getRequiredType();

        return Result.error(400,
                "参数 " + name + " 类型错误, 期望类型 " + requiredType.getSimpleName() + " 实际为 " + value);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return Result.error(400, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result<Void> DuplicateKeyExceptionHandler(DuplicateKeyException e) {
        return Result.error(400, "用户名已存在");
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> exceptionHandler(Exception e) {
        e.printStackTrace(); // 打印日志
        return Result.error(500, e.getMessage());
    }
}
