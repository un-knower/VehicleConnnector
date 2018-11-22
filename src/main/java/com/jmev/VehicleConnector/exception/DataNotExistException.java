package com.jmev.VehicleConnector.exception;

/**
 * 数据不存在异常
 *
 * @author Jun
 * @date 2018-11-19 11:10
 */
public class DataNotExistException extends GlobalException {
    public DataNotExistException() {
        super();
    }

    public DataNotExistException(String message) {
        super(message);
    }

    public DataNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotExistException(Throwable cause) {
        super(cause);
    }

    protected DataNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
