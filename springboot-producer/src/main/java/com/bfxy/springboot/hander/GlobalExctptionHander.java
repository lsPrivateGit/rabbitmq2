package com.bfxy.springboot.hander;

import com.bfxy.springboot.domain.ResponseBo;
import com.bfxy.springboot.exception.LimitAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: LinSong
 * @Date: 2019/4/12 15:52
 */
@RestControllerAdvice
public class GlobalExctptionHander {

    @ExceptionHandler(value = LimitAccessException.class)
    public ResponseBo handleLimitAccessException(LimitAccessException e) {
        return ResponseBo.error(e.getMessage());
    }

}
