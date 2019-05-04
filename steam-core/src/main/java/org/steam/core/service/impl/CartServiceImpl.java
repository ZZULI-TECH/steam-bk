package org.steam.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steam.common.exception.ServiceException;
import org.steam.core.model.entity.Cart;
import org.steam.core.model.entity.Game;
import org.steam.core.model.vo.CartListVo;
import org.steam.core.repository.CartMapper;
import org.steam.core.service.ICartService;
import org.steam.core.service.IGameService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private IGameService gameService;

    /**
     * @author biao
     * @param cart 游戏id ，用户id
     */
    @Override
    public void addToCart(Cart cart) throws ServiceException {
        Cart haveCart = this.baseMapper.selectOne(new QueryWrapper<Cart>().eq("game_id", cart.getGameId()));
        if(haveCart==null){
            //购物车尚未拥有
            Game game = gameService.getById(cart.getGameId());
            if(game.getOnSale()){
                cart.setGameName(game.getName())
                        .setGameNum(1)
                        .setGamePrice(game.getPrice())
                        .setSelected(true)
                        .setGameImage(game.getCover())
                        .setGameType(game.getType());
                this.baseMapper.insert(cart);
            }else{
                throw new ServiceException(1004L, "game is not on sale");
            }
        }else{
            //购物车已经含有,添加数量即可
            haveCart.setGameNum(haveCart.getGameNum()+1);
            this.baseMapper.updateById(haveCart);
        }

    }
    /**
     * @author biao
     * @param cart 购物车id，用户id
     */
    @Override
    public void removeFromCart(Cart cart) {
        Map<String,Object> removeParam=new HashMap<>();
        removeParam.put("id",cart.getId());
        removeParam.put("user_id",cart.getUserId());
        this.baseMapper.deleteByMap(removeParam);
    }
    /**
     * @author biao
     * @param cart 购物车id ，用户id
     */
    @Override
    public void selectCart(Cart cart) {
        Cart updateCart=new Cart().setSelected(true);
        this.baseMapper.update(updateCart,new UpdateWrapper<Cart>()
                .eq("id",cart.getId())
                .eq("user_id",cart.getUserId()));
    }

    @Override
    public void cancelSelect(Cart cart) {
        Cart updateCart=new Cart().setSelected(false);
        this.baseMapper.update(updateCart,new UpdateWrapper<Cart>()
                .eq("id",cart.getId())
                .eq("user_id",cart.getUserId()));
    }

    /**
     * @author biao
     * @param cart 用户id
     */
    @Override
    public void selectAllCart(Cart cart) {
        Cart updateCart=new Cart().setSelected(true);
        this.baseMapper.update(updateCart,new UpdateWrapper<Cart>()
                .eq("user_id",cart.getUserId()));
    }

    @Override
    public void cancelSelectAllCart(Cart cart) {
        Cart updateCart=new Cart().setSelected(false);
        this.baseMapper.update(updateCart,new UpdateWrapper<Cart>()
                .eq("user_id",cart.getUserId()));
    }

    /**
     * @author biao
     * @param uid 用户id
     * @return List<Cart> 购物车列表
     */
    @Override
    public CartListVo listMyCart(Long uid) {
        CartListVo cartList=new CartListVo();
        List<Cart> carts = this.baseMapper.selectList(new QueryWrapper<Cart>()
                .eq("user_id", uid));
        cartList.setCartList(carts);
        cartList.setTotalPrice(countPrice(carts));
        return cartList;
    }

    /**
     * @author biao
     * @param cartList 购物车列表
     * @return BigDecimal购物车中选中的商品的价格
     */
    private BigDecimal countPrice(List<Cart> cartList){
        BigDecimal price=BigDecimal.valueOf(0.00);
        for (Cart cart : cartList){
            if(cart.getSelected()){
                price=price.add(cart.getGamePrice().multiply(BigDecimal.valueOf(cart.getGameNum())));
            }
        }
        return price;
    }

}
