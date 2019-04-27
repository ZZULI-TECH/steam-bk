package org.steam.common.exception;

/**
 *  参数异常
 *
 * @author mingshan
 */
public class ParameterException extends RuntimeException {
    private static final long serialVersionUID = -7235137272015389906L;
    private String message;

    public ParameterException() { }

    public ParameterException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
