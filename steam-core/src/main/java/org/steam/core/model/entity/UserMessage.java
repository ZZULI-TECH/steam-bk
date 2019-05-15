package org.steam.core.model.entity;

import org.steam.common.model.VersionEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @since 2019-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user_message")
public class UserMessage extends VersionEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 消息内容
     */
    private Boolean message;

    /**
     * 1提醒 2公告
     */
    private Boolean category;

    /**
     * 0未读，1已读
     */
    private Boolean status;



}
