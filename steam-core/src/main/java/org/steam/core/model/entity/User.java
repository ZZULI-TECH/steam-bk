package org.steam.core.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.steam.common.model.VersionEntity;

/**
 * 用户实体
 *
 * @author mingshan biao
 */
@Data
@TableName(value = "t_users")
public class User extends VersionEntity {
    private static final long serialVersionUID = 1132267485791852991L;

    /**
     * 主键ID
     */
    @TableId(value = "u_id", type = IdType.AUTO)
    private long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     * 注解@TableField可以手动指定映射的数据库字段名
     */
    @TableField("email")
    private String eeee;

    /**
     * 地址
     */
    private String address;

    /**
     * 下划线字段示例
     * 由于在yml中配置了驼峰规则，划线字段的驼峰自动转换
     */
    private String doubleWords;

}
