package org.steam.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 封装结果实体类
 *
 * @author mingshan
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResultModel<T> implements Serializable {
    private static final long serialVersionUID = -3161231835832768735L;

    private long code;
    private String message;
    private T content;

    public ResultModel() {}

    public ResultModel(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultModel(long code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }
}
