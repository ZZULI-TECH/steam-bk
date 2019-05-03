package org.steam.core.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.steam.core.model.entity.Cart;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
public class CartListVo {
    /**
     * 购物车列表
     */
    private List<Cart> cartList;
    /**
     * 总价
     */
    private BigDecimal totalPrice;

}
