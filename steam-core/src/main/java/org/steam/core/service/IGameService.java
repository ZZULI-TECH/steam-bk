package org.steam.core.service;

import org.steam.common.exception.ServiceException;
import org.steam.common.exception.VersionException;
import org.steam.core.model.entity.Game;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author biao
 * @since 2019-04-28
 */
public interface IGameService extends IService<Game> {

    /**
     * 获取游戏信息，包含评论
     *
     * @param id 游戏id
     * @return 商品信息
     */
    Game get(Long id);

    /**
     * 游戏上架
     *
     * @param id 游戏id
     * @param version 当前版本
     */
    void putaway(Long id, Long version) throws ServiceException, VersionException;

    /**
     * 游戏下架
     *
     * @param id 游戏id
     * @param version 当前版本
     */
    void offShelve(Long id, Long version) throws ServiceException, VersionException;
}
