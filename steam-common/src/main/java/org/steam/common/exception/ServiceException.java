package org.steam.common.exception;

/**
 * 服务异常
 *
 * @author mingshan
 */
public class ServiceException extends BaseCodeMessageException {
    private static final long serialVersionUID = -8183259784734482522L;

    public ServiceException() { }

    public ServiceException(long code, String message) {
        super(code, message);
    }
}
