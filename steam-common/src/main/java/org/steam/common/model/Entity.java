package org.steam.common.model;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体（数据库映射）
 *
 * @author mingshan
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Entity implements Serializable {
    private static final long serialVersionUID = -6785900030151893453L;

    // id 唯一标识
    private Long id;
    // 创建时间
    private LocalDateTime gmtCreate;
    // 修改时间
    private LocalDateTime gmtModified;
    // 逻辑删除 (0：逻辑未删除  1：逻辑已删除)
    @TableLogic
    private Integer deleted;
}
