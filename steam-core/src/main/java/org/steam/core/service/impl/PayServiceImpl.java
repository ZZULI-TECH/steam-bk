package org.steam.core.service.impl;

import com.alipay.api.domain.AlipayTradePagePayModel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.jpay.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steam.common.constant.OrderStatusEnum;
import org.steam.common.exception.ServiceException;
import org.steam.core.model.entity.AliPayBean;
import org.steam.core.model.entity.Order;
import org.steam.core.model.entity.OrderGame;
import org.steam.core.model.vo.PayPageVO;
import org.steam.core.service.IGameLibraryService;
import org.steam.core.service.IOrderGameService;
import org.steam.core.service.IOrderService;
import org.steam.core.service.IPayService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 支付服务实现类
 *
 */

@Service
public class PayServiceImpl implements IPayService {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderGameService orderGameService;
    @Autowired
    private IGameLibraryService gameLibraryService;
    @Autowired
    private AliPayBean aliPayBean;

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


    @Override
    @Transactional(rollbackFor = Exception.class)
    public PayPageVO payOrder(Long orderId, Long uid) {
        Order order = orderService.getById(orderId);
        List<OrderGame> orderGames = orderGameService.list(new QueryWrapper<OrderGame>().eq("order_id", orderId));
        String totalAmount = order.getTotalAmount().toString();
        String outTradeNo = order.getOrderSn();
        //指定返回地址
        String returnUrl = aliPayBean.getDomain() + "/api/pay/return";
        //指定通知地址
        String notifyUrl = aliPayBean.getDomain() + "/api/pay/notify";
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(outTradeNo);
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        model.setTotalAmount(totalAmount);
        model.setSubject(orderGames.get(0).getGameName()+"等游戏");
        model.setBody("订单支付");
        model.setPassbackParams("passback_params");
        PayPageVO payPage=new PayPageVO();
        payPage.setModel(model)
                .setNotifyUrl(notifyUrl)
                .setReturnUrl(returnUrl);
        return payPage;
    }

    @Override
    public void payCallBack(Map<String, String> callBackParam) throws ServiceException {
        synchronized (this){
            Order order = orderService.getById(callBackParam.get("out_trade_no"));
            if(order == null){
                log.error("订单不存在");
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
            gameLibraryService.addToLibrary(order.getId(),order.getUid());
        }

    }


}
