package org.steam.core.model.entity;

import lombok.Data;
import org.steam.common.model.VersionEntity;

/**
 * 用户实体
 *
 * @author mingshan
 */
@Data
public class User extends VersionEntity {
    private static final long serialVersionUID = 1132267485791852991L;

    private String name;
}
