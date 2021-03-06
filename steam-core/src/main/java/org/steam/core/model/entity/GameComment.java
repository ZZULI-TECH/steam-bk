package org.steam.core.model.entity;

import org.steam.common.model.Entity;
import org.steam.common.model.VersionEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_game_comment")
public class GameComment extends Entity {

    private static final long serialVersionUID = 1L;

    private Long uid;

    private Long gameId;

    private String content;

}
