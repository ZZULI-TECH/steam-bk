package org.steam.core.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.steam.common.model.VersionEntity;

/**
 * <p>
 * 用户游戏库
 * </p>
 *
 * @author biao
 * @since 2019-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_game_library")
public class GameLibrary extends VersionEntity {
    private static final long serialVersionUID = -91297063149044971L;

    /**
     * 用户id
     */
    private long uid;

    /**
     * 游戏id
     */
    private long gameId;

}
