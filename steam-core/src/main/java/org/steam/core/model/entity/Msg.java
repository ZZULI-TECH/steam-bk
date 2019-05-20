package org.steam.core.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import org.steam.common.model.VersionEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *  用户留言表
 * </p>
 *
 * @since 2019-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_msg")
public class Msg extends VersionEntity {

    private static final long serialVersionUID = 1L;

    private Long uid;

    private String msg;

    /**
     * 留言回复
     */
    private String msgBk;


    @TableField(exist = false)
    private String name;
    @TableField(exist = false)
    private String age;
    @TableField(exist = false)
    private String sex;




}
