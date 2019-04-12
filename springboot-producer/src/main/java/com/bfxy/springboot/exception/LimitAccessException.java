package com.bfxy.springboot.exception;

/**
 * @Author: LinSong
 * @Date: 2019/4/12 15:49
 */
public class LimitAccessException extends RuntimeException {

    private static final long serialVersionUID = -9167891400537899598L;

    public LimitAccessException(String message) {
        super(message);
    }
}
