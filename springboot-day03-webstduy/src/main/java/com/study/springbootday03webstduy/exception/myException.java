package com.study.springbootday03webstduy.exception;

public class myException extends RuntimeException {
    public myException() {
        super("用户不存在！");
    }
}
