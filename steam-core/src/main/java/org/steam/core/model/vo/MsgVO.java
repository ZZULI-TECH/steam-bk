package org.steam.core.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class MsgVO  implements Serializable {

    private static final long serialVersionUID = -3731404669199900054L;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 留言
     */
    private String msg;


}
