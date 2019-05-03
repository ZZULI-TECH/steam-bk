package org.steam.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
     */
    void addToCart(Cart cart);

    /**
     * 从购物车移除
     * @param cart 购物车id，用户id
     */
    void removeFromCart(Cart cart);

    /**
     * 选中购物车的商品
     * @param cart 购物车id ，用户id
     */
    void selectCart(Cart cart);

    /**
     * 取消选中购物车的商品
     * @param cart 购物车id ，用户id
     */
    void selectAllCart(Cart cart);

    /**
     * 用户的购物车内容
     * @param uid 用户id
     * @return Cart列表
     */
    CartListVo listMyCart(Long uid);

}
