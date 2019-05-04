package org.steam.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steam.common.exception.ServiceException;
import org.steam.core.model.entity.Cart;
import org.steam.core.model.entity.Game;
import org.steam.core.model.entity.OrderGame;
import org.steam.core.repository.OrderGameMapper;
import org.steam.core.service.IGameService;
import org.steam.core.service.IOrderGameService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 创建订单的游戏列表
     * @param cartList 购物车列表
     * @param orderId 订单id
     * @throws ServiceException 订单异常
     */
    @Override
    public void createOrderGameFromCart(List<Cart> cartList,Long orderId) throws ServiceException {
        if(cartList==null){
            throw new ServiceException(1000L, "cart is empty");
        }
        List<OrderGame> orderGames=new ArrayList<>();
        for (Cart cart:cartList) {
            if(cart.getSelected()){
                OrderGame orderGame=new OrderGame();
                orderGame.setOrderId(orderId)
                        .setGameCover(cart.getGameImage())
                        .setGameId(cart.getGameId())
                        .setGameName(cart.getGameName())
                        .setGameNum(cart.getGameNum())
                        .setGamePrice(cart.getGamePrice())
                        .setGameType(cart.getGameType())
                        .setCostPrice(cart.getGamePrice().multiply(BigDecimal.valueOf(cart.getGameNum())));
            orderGames.add(orderGame);
            }
        }
        if(orderGames.size() == 1){
            this.baseMapper.insert(orderGames.get(0));
        }
        this.saveBatch(orderGames);
    }

    @Override
    public void createOrderGameQuickly(Game game, Long orderId ,Integer num) throws ServiceException {
        if(game.getOnSale()){
            OrderGame orderGame=new OrderGame();
            orderGame.setOrderId(orderId)
                    .setGameCover(game.getCover())
                    .setGameId(game.getId())
                    .setGameName(game.getName())
                    .setGameNum(num)
                    .setGamePrice(game.getPrice())
                    .setGameType(game.getType())
                    .setCostPrice(game.getPrice().multiply(BigDecimal.valueOf(num)));
            this.baseMapper.insert(orderGame);
        }else {
            throw new ServiceException(1004L, "game is not on sale");
        }
    }
}
