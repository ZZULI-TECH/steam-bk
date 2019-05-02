package org.steam.common.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.steam.common.constant.Constants;

import java.io.Serializable;

/**
 * 封装结果实体类
 *
 * @author mingshan
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class ResultModel<T> implements Serializable {
    private static final long serialVersionUID = -3161231835832768735L;

    private long code;
    private String message;
    private T content;

    public ResultModel(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultModel(long code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public static <T> ResultModel<T> ok() {
        return ok(null);
    }

    public static <T> ResultModel<T> ok(T content) {
        return ResultModel.<T>builder()
                .code(0L)
                .message(Constants.RESPONSE_SUCCESS)
                .content(content)
                .build();
    }

    public static <T> ResultModel<T> fail(long code, String message) {
        return ResultModel.<T>builder()
                .code(code)
                .message(message)
                .build();
    }
}
