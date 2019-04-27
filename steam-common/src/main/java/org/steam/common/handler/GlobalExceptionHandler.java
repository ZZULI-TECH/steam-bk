package org.steam.common.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.steam.common.exception.ParameterException;
import org.steam.common.exception.ServiceException;
import org.steam.common.model.ResultModel;

/**
 * 公共异常处理
 *
 * @author mingshan
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // #issue 02
    @ResponseBody
    public String handleInvalidRequestError(ParameterException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResultModel> handleServiceException(ServiceException ex) {
        return new ResponseEntity<ResultModel>(ex.getResult(), ex.getHttpStatus());
    }
}
