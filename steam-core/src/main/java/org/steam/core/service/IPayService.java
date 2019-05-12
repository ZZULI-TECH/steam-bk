package org.steam.core.service;

import org.steam.common.exception.ServiceException;
import org.steam.core.model.vo.PayPageVO;

import java.util.Map;

/**
 * 支付服务
 */

public interface IPayService {


    /**
     * 支付订单
     * @param orderId 订单id
     * @param uid 用户id
     * @throws ServiceException 系统服务一场
     * @author biao
     */
    void pay(Long orderId,Long uid) throws ServiceException;


    /**
     * 支付回调信息
     * @param callBackParam
     */
    void payCallBack(Map<String, String> callBackParam) throws ServiceException;

}
