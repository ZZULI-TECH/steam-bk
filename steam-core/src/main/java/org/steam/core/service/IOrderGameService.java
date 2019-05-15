package org.steam.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.steam.common.exception.ServiceException;
import org.steam.core.model.entity.Cart;
import org.steam.core.model.entity.Game;
import org.steam.core.model.entity.OrderGame;
import org.steam.core.model.vo.CartListVo;

import java.util.List;

/**
 * <p>
 *  订单商品服务类
 * </p>
 *
 * @since 2019-05-02
 */
public interface IOrderGameService extends IService<OrderGame> {

    /**
     * 添加从购物车创建订单时的游戏列表
     * @param cartList 购物车列表
     */
    void createOrderGameFromCart(List<Cart> cartList,Long orderId) throws ServiceException;

    /**
     * 直接创建订单时的游戏列表
     * @param game 游戏信息
     * @param orderId 订单id
     * @param num 购买数量
     */
    void createOrderGameQuickly(Game game,Long orderId,Integer num) throws ServiceException;

}
