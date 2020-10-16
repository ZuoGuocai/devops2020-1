package com.ypc.devopstest.util;

import com.ypc.devopstest.config.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private int code;

    private T data;

    private String message;

    public Result(Integer successCode, T obj, String successContent) {
        this.code = successCode;
        this.data = obj;
        this.message = successContent;
    }

    public static Result ok() {
        return new Result<>(Code.SUCCESS_CODE, null,Code.SUCCESS_CONTENT);
    }

    public static <T> Result ok(T obj) {
        return new Result<>(Code.SUCCESS_CODE, obj,Code.SUCCESS_CONTENT);
    }

    public static <T> Result error(int status, String message) {
        return new Result<>(status, null,message);
    }

    public static <T> Result ok(int status, T obj) {
        return new Result<>(status, obj,Code.SUCCESS_CONTENT);
    }


}

