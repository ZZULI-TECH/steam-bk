package org.steam.common.exception;

import org.springframework.http.HttpStatus;
import org.steam.common.model.ResultModel;

/**
 * 服务异常
 *
 * @author mingshan
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -8183259784734482522L;
    private ResultModel result;
    private HttpStatus httpStatus;

    public ServiceException() { }

    public ServiceException(ResultModel result, HttpStatus httpStatus) {
        this.result = result;
        this.httpStatus = httpStatus;
    }

    public ResultModel getResult() {
        return this.result;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
