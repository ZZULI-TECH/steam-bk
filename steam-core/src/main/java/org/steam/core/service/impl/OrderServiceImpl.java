package org.steam.core.service.impl;

import org.steam.core.model.entity.Order;
import org.steam.core.repository.OrderMapper;
import org.steam.core.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
