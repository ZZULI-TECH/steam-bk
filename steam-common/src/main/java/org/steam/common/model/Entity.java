package org.steam.common.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体（数据库映射）
 *
 * @author mingshan
 */
@Data
public class Entity implements Serializable {
    private static final long serialVersionUID = -6785900030151893453L;

    // id 唯一标识
    private long id;
    // 创建时间
    private LocalDateTime gmtCreate;
    // 修改时间
    private LocalDateTime gmtModified;
}
