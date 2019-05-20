package org.steam.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.steam.core.model.entity.Msg;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @since 2019-05-20
 */
public interface IMsgService extends IService<Msg> {

    Page<Msg> selectMsgList(Integer page,Integer size);

}
