package org.steam.core.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steam.common.constant.GameTypeEnum;
import org.steam.common.constant.OrderStatusEnum;
import org.steam.common.exception.ServiceException;
import org.steam.core.model.entity.Cart;
import org.steam.core.model.entity.Game;
import org.steam.core.model.entity.Order;
import org.steam.core.model.entity.User;
import org.steam.core.model.vo.CartListVo;
import org.steam.core.model.vo.OrderListVo;
import org.steam.core.repository.OrderMapper;
import org.steam.core.service.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2019-04-28
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ICartService cartService;
    @Autowired
    private IOrderGameService orderGameService;
    @Autowired
    private UserService userService;
    @Autowired
    private IGameService gameService;


    @Override
    @Transactional
    public  Long createOrderFromCart(Long uid) throws ServiceException {
        synchronized (this){
        CartListVo cartListVo = cartService.listMyCart(uid);
            Order order=new Order();
            order.setOrderSn(IdWorker.getTimeId());
            order.setUid(uid);
            order.setOrderAmount(cartListVo.getTotalPrice());
            order.setNeedShipping(needShipping(cartListVo.getCartList()));
            if(order.getNeedShipping()){
                //需要发货，设置发货地址
                User user = userService.getById(uid);
                order.setAddress(user.getAddress());
                order.setConsignee(user.getName());
            }
            //创建订单
            this.baseMapper.insert(order);
            //生成订单游戏列表
            orderGameService.createOrderGameFromCart(cartListVo.getCartList(),order.getId());
            //清除购物车
            cartService.removeAll(uid);
            return  order.getId();
        }

    }

    @Override
    public Long createOrderQuickly(Long uid, Long gameId, Integer num) throws ServiceException {
        Game game = gameService.getById(gameId);
        synchronized (this){
            Order order=new Order();
            order.setOrderSn(IdWorker.getTimeId());
            order.setUid(uid);
            order.setOrderAmount(game.getPrice().multiply(BigDecimal.valueOf(num)));
            order.setNeedShipping(GameTypeEnum.COMMON.getCode().equals(game.getType()));
            if(order.getNeedShipping()){
                //需要发货，设置发货地址
                User user = userService.getById(uid);
                order.setAddress(user.getAddress());
                order.setConsignee(user.getName());
            }
            //创建订单
            this.baseMapper.insert(order);
            //生成订单游戏列表
            orderGameService.createOrderGameQuickly(game,order.getId(),num);
            return  order.getId();
        }

    }

    @Override
    public void cancelOrder(Long id, Long uid) throws ServiceException {
        Order order = this.baseMapper.selectById(id);
        if(order == null){
            log.error("订单不存在");
            throw new ServiceException(1005, "order not exist");
        }
        if(!order.getUid().equals(uid)){
            log.error("不是此人订单");
            throw new ServiceException(1005, "order not exist");
        }
        if(!OrderStatusEnum.CREATED.getCode().equals(order.getOrderStatus())){
            log.error("只有待支付订单允许取消");
            throw new ServiceException(1006, "only allow need pay order do this");
        }
        order.setOrderStatus(OrderStatusEnum.CANCLED.getCode());
        this.baseMapper.updateById(order);
    }

    @Override
    public Page<OrderListVo> orderList(Integer pageNum, Integer pageSize, Order order) {
        if(pageNum==null){
            pageNum=1;
        }
        if(pageSize==null){
            pageSize=30;
        }
        Page<OrderListVo> page=new Page<>(pageNum,pageSize);
        page.setRecords(this.baseMapper.selectOrderListByUid(page,order));
        return page;
    }

    @Override
    public Order orderInfo(Long id) {
        return this.baseMapper.selectOrderByOrderId(id);
    }

    /**
     * 校验游戏是否需要发货
     * @param cartList 购物车列表
     * @return boolean
     */
    private boolean needShipping(List<Cart> cartList){
        for (Cart cart : cartList) {
            if(GameTypeEnum.COMMON.getCode().equals(cart.getGameType())){
                return true;
            }
        }
        return false;
    }

}
