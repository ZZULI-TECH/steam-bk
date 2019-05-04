package org.steam.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.steam.common.exception.ServiceException;
import org.steam.core.model.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import org.steam.core.model.vo.OrderListVo;

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
    void createOrderFromCart(Long uid) throws ServiceException;

    /**
     * 商品立即下单
     * @param uid 用户id
     * @param gameId 游戏id
     * @param num 数量
     */
    void createOrderQuickly(Long uid , Long gameId ,Integer num) throws ServiceException;

    /**
     * 取消订单
     * @param id 订单id
     * @param uid 用户id
     */
    void cancelOrder(Long id , Long uid) throws ServiceException;

    /**
     * 订单列表
     * @param order 用户id ，订单状态
     */
    Page<OrderListVo> orderList(Integer pageNum, Integer pageSize, Order order);

    Order orderInfo(Long id);

}
