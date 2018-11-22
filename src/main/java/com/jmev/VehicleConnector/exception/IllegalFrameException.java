package com.jmev.VehicleConnector.exception;

import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import lombok.Getter;

/**
 * 非法数据帧异常
 *
 * @author Jun
 * @date 2018-11-19 11:24
 */
@Getter
public class IllegalFrameException extends GlobalException {

    private BaseFrame baseFrame;

    public IllegalFrameException() {
        super();
    }

    /**
     * 构建包含非法数据的异常
     *
     * @param message 异常消息
     * @param frame   非法数据帧
     */
    public IllegalFrameException(String message, BaseFrame frame) {
        super(message);
        this.baseFrame = frame;
    }

    public IllegalFrameException(String message) {
        super(message);
    }

    public IllegalFrameException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalFrameException(Throwable cause) {
        super(cause);
    }

    protected IllegalFrameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
