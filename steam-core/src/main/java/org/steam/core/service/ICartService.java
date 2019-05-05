package org.steam.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.steam.common.exception.ServiceException;
import org.steam.core.model.entity.Cart;
import org.steam.core.model.vo.CartListVo;

import java.util.List;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author biao
 * @since 2019-04-28
 */
public interface ICartService extends IService<Cart> {

    /**
     * 将游戏加入购物车
     * @param cart 游戏id ，用户id
     * @author biao
     */
    void addToCart(Cart cart) throws ServiceException;

    /**
     * 从购物车移除
     * @param cart 购物车id，用户id
     * @author biao
     */
    void removeFromCart(Cart cart);

    /**
     * 选中购物车的游戏
     * @param cart 购物车id ，用户id
     * @author biao
     */
    void selectCart(Cart cart);

    /**
     * 取消选中购物车的游戏
     * @param cart 购物车id ，用户id
     * @author biao
     */
    void cancelSelect(Cart cart);

    /**
     * 选中购物车的所有游戏
     * @param cart 用户id
     * @author biao
     */
    void selectAllCart(Cart cart);

    /**
     * 选中购物车的所有游戏
     * @param cart 用户id
     * @author biao
     */
    void cancelSelectAllCart(Cart cart);

    /**
     * 用户的购物车内容
     * @param uid 用户id
     * @return Cart列表
     * @author biao
     */
    CartListVo listMyCart(Long uid);

    /**
     * 清空购物车
     * @param uid 用户id
     * @author biao
     */
    void removeAll(Long uid);

}
