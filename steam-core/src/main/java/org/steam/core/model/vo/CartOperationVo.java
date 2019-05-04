package org.steam.core.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartOperationVo {

    @ApiModelProperty(value="购物车id",name="id",required=true)
    private Long id;

}
