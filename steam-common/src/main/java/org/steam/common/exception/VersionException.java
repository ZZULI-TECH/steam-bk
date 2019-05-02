package org.steam.common.exception;

/**
 * 版本号异常
 *
 * @author mingshan
 */
public class VersionException extends Exception {
    private static final long serialVersionUID = -8183259784734482522L;
    private String message;

    public VersionException() { }

    public VersionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
