package org.steam.core.service.impl;

import org.steam.core.model.entity.OrderGame;
import org.steam.core.repository.OrderGameMapper;
import org.steam.core.service.IOrderGameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author biao
 * @since 2019-05-02
 */
@Service
public class OrderGameServiceImpl extends ServiceImpl<OrderGameMapper, OrderGame> implements IOrderGameService {

}
