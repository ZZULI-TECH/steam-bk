package org.steam.core.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddCartVo {

    @ApiModelProperty(value="游戏id",name="gameId",required=true)
    private Long gameId;

}
