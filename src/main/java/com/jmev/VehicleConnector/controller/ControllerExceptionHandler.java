package com.jmev.VehicleConnector.controller;

import com.jmev.VehicleConnector.entity.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author Jun
 * @date 2018-11-16 12:32
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 未知异常捕获
     *
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public Response throwableHandle(Throwable e) {
        String msg = e.getMessage();

        log.error(msg, e);
        return Response.error(msg);
    }

}
