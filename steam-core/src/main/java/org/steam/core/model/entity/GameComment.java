package org.steam.core.model.entity;

import org.steam.common.model.VersionEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_game_comment")
public class GameComment extends VersionEntity {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long gameId;

    private String content;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;


}
