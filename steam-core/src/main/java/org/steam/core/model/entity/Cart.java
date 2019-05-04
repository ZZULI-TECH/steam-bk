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
    * 购物车表
    * </p>
*
* @author biao
* @since 2019-04-28
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cart")
public class Cart extends VersionEntity {

    private static final long serialVersionUID = 1L;

    /**
      * 	用户id
      */
    private Long userId;

    /**
      * 游戏id
      */
    private Long gameId;

    /**
      * 游戏名称
      */
    private String gameName;

    /**
      * 游戏价格
      */
    private BigDecimal gamePrice;

    /**
     * 数量
     */
    private Integer gameNum;

    /**
     * 游戏图片
     */
    private String gameImage;

    /**
     * 游戏类型
     */
    private Integer gameType;

    /**
     * 是否选中
     */
    private Boolean selected;



}
