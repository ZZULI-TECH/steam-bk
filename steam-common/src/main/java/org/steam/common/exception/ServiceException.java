package org.steam.common.exception;

/**
 * 服务异常
 *
 * @author mingshan
 */
public class ServiceException extends Exception {
    private static final long serialVersionUID = -8183259784734482522L;
    private long code;
    private String message;

    public ServiceException() { }

    public ServiceException(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public long getCode() {
        return this.code;
    }
}
