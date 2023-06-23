package com.example.exception;

import com.example.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice(basePackages = "com.example")
public class ExceptionControllerAdvice {
    /**
     * 捕获全局异常
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        e.printStackTrace();
        return R.error().message("服务器内部错误");
    }
}
