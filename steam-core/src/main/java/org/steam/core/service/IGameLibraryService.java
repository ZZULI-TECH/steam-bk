package org.steam.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.steam.core.model.entity.GameLibrary;
import org.steam.core.model.entity.Order;
import org.steam.core.model.vo.GameLibraryVO;

/**
 * <p>
 * 用户游戏库 服务类
 * </p>
 *
 * @since 2019-04-28
 */
public interface IGameLibraryService extends IService<GameLibrary> {

    /**
     * 将订单完成的游戏放入用户的游戏库
     * @param orderId 订单id
     * @param uid 用户id
     * @author biao
     */
    void addToLibrary(Long orderId,Long uid);

    /**
     * 获取用户的游戏库
     * @param page 页码
     * @param size 每页数量
     * @param uid 用户id
     * @author biao
     */
    Page<GameLibraryVO> listLibrary(Integer page, Integer size, Long uid);

    Order getOreder(Long uid, Long gameId);
}
