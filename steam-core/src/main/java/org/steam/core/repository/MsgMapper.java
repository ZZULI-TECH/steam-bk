package org.steam.core.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.steam.core.model.entity.Msg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @since 2019-05-20
 */
public interface MsgMapper extends BaseMapper<Msg> {

    Page<Msg> selectMsgList(Page<Msg> page);

}
