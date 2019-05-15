package org.steam.core.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.steam.core.model.entity.Order;
import org.steam.core.model.entity.OrderGame;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 订单列表返回参数
 * @author biao
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = {"handler"})
public class OrderListVo {
    //订单id
    private Long id;
    //流水号
    private String orderSn;
    //订单状态
    private Integer orderStatus;
    //订单金额
    private BigDecimal orderAmount;
    //订单生成时间
    private LocalDate gmtCreate;
    //订单包含的游戏
    private List<OrderGame> orderGames;
}
