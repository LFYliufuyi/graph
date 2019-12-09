package com.xnzn.graph.utils;

import lombok.Getter;

/**
 * @Auther: xn
 * @Date: 2019/9/29 14:26
 */
public enum ErrorEnum {

    SYSTEM_ERROR(4000, "系统错误");

    @Getter
    private final Integer code;

    @Getter
    private final String message;

    @Getter
    private final Boolean success = false;

    private ErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return success + "/" + code + "/" + message;
    }
}
