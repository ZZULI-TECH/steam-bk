package org.steam.core.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.steam.common.model.Entity;

/**

 */
@Getter
@Setter
@ToString
public class GameImageVO extends Entity {
    private static final long serialVersionUID = -6136982141442258669L;
    /**
     * 游戏id
     */
    private Long gameId;

    /**
     * 图片地址
     */
    private String imageUrl;

}
