package org.steam.core.model.entity;

import org.steam.common.model.VersionEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 游戏id
     */
    private Long gameId;

    private Long version;



}
