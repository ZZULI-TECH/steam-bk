package org.steam.core.model.entity;

import org.steam.common.model.Entity;
import org.steam.common.model.VersionEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @since 2019-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_game_image")
public class GameImage extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 游戏id
     */
    private Long gameId;

    /**
     * 图片地址
     */
    private String imageUrl;



}
