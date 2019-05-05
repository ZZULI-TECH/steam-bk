package org.steam.core.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steam.common.constant.OrderStatusEnum;
import org.steam.common.exception.ServiceException;
import org.steam.core.model.entity.Order;
import org.steam.core.service.IGameLibraryService;
import org.steam.core.service.IOrderService;
import org.steam.core.service.IPayService;

import java.time.LocalDateTime;

/**
 * 支付服务实现类
 *
 */

@Service
public class PayServiceImpl implements IPayService {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IGameLibraryService gameLibraryService;
    private Log log = LogFactory.getLog(this.getClass());
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pay(Long orderId, Long uid) throws ServiceException {
        synchronized (this){
            Order order = orderService.getById(orderId);
            if(order == null){
                log.error("订单不存在");
                throw new ServiceException(1005, "order not exist");
            }
            if(!order.getUid().equals(uid)){
                log.error("不是此人订单");
                throw new ServiceException(1005, "order not exist");
            }
            if (!OrderStatusEnum.CREATED.getCode().equals(order.getOrderStatus())){
                log.error("订单已支付");
                throw new ServiceException(1006, "order has paid");
            }
            if(order.getNeedShipping()){
                //需要发货，订单置为已发货状态
                order.setOrderStatus(OrderStatusEnum.NEED_SHIPPING.getCode());
            }else {
                //不需要发货，订单设为已完成
                order.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
                order.setConfirmTime(LocalDateTime.now());
            }
            order.setTotalAmount(order.getOrderAmount())
                    .setPayCode(IdWorker.getTimeId())
                    .setPayName("wxPay")
                    .setPayTime(LocalDateTime.now());
            orderService.updateById(order);
        }
        //数字游戏入库
        gameLibraryService.addToLibrary(orderId,uid);
    }
}
