package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务异常类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonException extends RuntimeException {
    private Integer code;
    private String msg;
}
