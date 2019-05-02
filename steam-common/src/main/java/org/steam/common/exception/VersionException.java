package org.steam.common.exception;

/**
 * 版本号异常
 *
 * @author mingshan
 */
public class VersionException extends BaseCodeMessageException {
    private static final long serialVersionUID = -8183259784734482522L;

    public VersionException() { }

    public VersionException(String message) {
        super(1000L, message);
    }

    public long getCode() {
        return 1000L;
    }
}
