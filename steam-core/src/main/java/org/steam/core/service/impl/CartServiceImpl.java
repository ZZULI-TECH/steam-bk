package org.steam.core.service.impl;

import org.steam.core.model.entity.Cart;
import org.steam.core.repository.CartMapper;
import org.steam.core.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author biao
 * @since 2019-04-28
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

}
