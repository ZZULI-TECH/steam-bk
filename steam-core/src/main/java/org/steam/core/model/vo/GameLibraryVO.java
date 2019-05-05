package org.steam.core.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.steam.common.model.Entity;
import org.steam.core.model.entity.Game;

/**
 * @author mingshan
 */
@Getter
@Setter
@ToString
public class GameLibraryVO extends Game {
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 游戏id
     */
    private Long gameId;
}
