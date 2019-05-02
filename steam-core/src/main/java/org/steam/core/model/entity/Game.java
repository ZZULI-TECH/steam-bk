package org.steam.core.model.entity;

import java.math.BigDecimal;

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
 * @author biao
 * @since 2019-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_game")
public class Game extends VersionEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 1 数字游戏 2实体游戏
     */
    private Boolean type;

    /**
     * 有英文名称用英文名称，无英文用拼音，方便字母检索
     */
    private String englishName;

    /**
     * 点击量
     */
    private Integer clickCount;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 封面
     */
    private String cover;

    /**
     * 关键词
     */
    private String keywords;

    /**
     * 简单描述
     */
    private String remark;

    /**
     * 详细描述
     */
    private String content;

    /**
     * 是否在售，（上架/下架）0：已下架  1：已上架
     */
    private String onSale;



}
