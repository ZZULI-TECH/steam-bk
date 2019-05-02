package org.steam.common.exception;

/**
 * @author mingshan
 */
public abstract class BaseCodeMessageException extends Exception {
    private static final long serialVersionUID = -8183259784734482522L;
    private long code;

    public BaseCodeMessageException() {
        super();
    }

    public BaseCodeMessageException(long code, String message) {
        super(message);
        this.code = code;
    }

    public long getCode() {
        return this.code;
    }
}
