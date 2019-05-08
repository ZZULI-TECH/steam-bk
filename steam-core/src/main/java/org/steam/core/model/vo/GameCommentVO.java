package org.steam.core.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.steam.common.model.Entity;

/**
 * @author mingshan
 */
@Getter
@Setter
@ToString
public class GameCommentVO extends Entity {

    private static final long serialVersionUID = 1040966253513753393L;
    private Long uid;

    private Long gameId;

    private String content;
}
