package org.steam.core.service;

import org.steam.core.model.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author biao
 * @since 2019-04-28
 */
public interface IOrderService extends IService<Order> {

    /**
     * 购物车商品创建订单
     * @param uid 用户id
     */
    void createOrderFromCart(Long uid);

    /**
     * 商品立即下单
     * @param uid 用户id
     * @param gameId 游戏id
     * @param num 数量
     */
    void createOrderQuickly(Long uid , Long gameId ,Integer num);

    /**
     * 取消订单
     * @param id 订单id
     * @param uid 用户id
     */
    void cancelOrder(Long id , Long uid);

    /**
     * 订单列表
     * @param order 用户id ，订单状态
     */
    List<Order> orderList(Integer pageNum, Integer pageSize, Order order);

}
