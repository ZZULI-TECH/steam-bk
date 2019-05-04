package org.steam.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.steam.core.model.entity.Order;
import org.steam.core.model.vo.CartListVo;
import org.steam.core.repository.OrderMapper;
import org.steam.core.service.ICartService;
import org.steam.core.service.IOrderGameService;
import org.steam.core.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author biao
 * @since 2019-04-28
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ICartService cartService;
    @Autowired
    private IOrderGameService orderGameService;

    @Override
    public void createOrderFromCart(Long uid) {
        CartListVo cartListVo = cartService.listMyCart(uid);

    }

    @Override
    public void createOrderQuickly(Long uid, Long gameId, Integer num) {

    }

    @Override
    public void cancelOrder(Long id, Long uid) {

    }

    @Override
    public List<Order> orderList(Integer pageNum, Integer pageSize, Order order) {
        return null;
    }
}
