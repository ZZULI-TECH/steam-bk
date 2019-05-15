package org.steam.common.model;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 带有乐观锁版本号的实体
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VersionEntity extends Entity {
    private static final long serialVersionUID = 7193087800976439514L;

    // 乐观锁版本号
    @Version
    private Long version;
}
