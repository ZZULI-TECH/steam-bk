package org.steam.common.model;

import lombok.Data;

/**
 * 带有乐观锁版本号的实体
 *
 * @author mingshan
 */
@Data
public class VersionEntity extends Entity {
    private static final long serialVersionUID = 7193087800976439514L;

    // 乐观锁版本号
    private long version;
}
