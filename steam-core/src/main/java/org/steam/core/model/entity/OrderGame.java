package org.steam.core.model.entity;

import java.math.BigDecimal;

import org.steam.common.model.VersionEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author biao
 * @since 2019-05-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_order_game")
public class OrderGame extends VersionEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 游戏id
     */
    private Long gameId;

    /**
     * 游戏名称（添加这些内容而不从游戏表获取是为了防止后台修改游戏信息）
     */
    private String gameName;

    /**
     * 游戏数量
     */
    private Integer gameNum;

    /**
     * 单个游戏金额
     */
    private BigDecimal gamePrice;

    /**
     * 总金额
     */
    private BigDecimal costPrice;

    /**
     * 游戏封面
     */
    private String gameCover;


}
