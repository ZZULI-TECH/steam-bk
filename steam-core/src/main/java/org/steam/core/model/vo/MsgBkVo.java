package org.steam.core.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MsgBkVo implements Serializable {
    private static final long serialVersionUID = -3775302041435181539L;

    private Long id;

    private String msgBk;

}
