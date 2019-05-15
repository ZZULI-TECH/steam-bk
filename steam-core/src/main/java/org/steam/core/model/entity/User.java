package org.steam.core.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.steam.common.model.VersionEntity;

/**
 * 用户实体
 *
 */
@Data
@TableName(value = "t_user")
public class User extends VersionEntity {
    private static final long serialVersionUID = 1132267485791852991L;
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 地址
     */
    private String address;
    /**
     * 密码
     */
    private String password;

}
