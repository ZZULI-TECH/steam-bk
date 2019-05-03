package org.steam.core.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.steam.common.model.VersionEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author mingshan
 */
@Getter
@Setter
@ToString
public class GameVO extends VersionEntity {
    private static final long serialVersionUID = 6877280617789416502L;

    /**
     * 名称
     */
    private String name;

    /**
     * 1 数字游戏 2实体游戏
     */
    private String type;

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
     * 下载链接
     */
    private String downloadUrl;

    /**
     * 是否在售，（上架/下架）0：已下架  1：已上架
     */
    private Boolean onSale;

    private List<GameCommentVO> comments;
}
