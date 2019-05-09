package org.steam.core.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.steam.common.annotation.Authorization;
import org.steam.common.exception.ServerException;
import org.steam.common.exception.ServiceException;
import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.Cart;
import org.steam.core.model.entity.Token;
import org.steam.core.model.entity.User;
import org.steam.core.model.vo.AddCartVo;
import org.steam.core.model.vo.CartListVo;
import org.steam.core.model.vo.CartOperationVo;
import org.steam.core.service.ICartService;
import org.steam.core.util.TokenUtil;

/**
 * <p>
 * 购物车表 前端控制器
 * </p>
 *
 * @author biao
 * @since 2019-04-28
 */
@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @ApiOperation(value="添加到购物车", httpMethod="POST")
    @Authorization
    @PostMapping("/addToCart/{userId}")
    public ResultModel addToCart(@PathVariable long userId, @RequestBody AddCartVo addCartParam){
        ResultModel<Token> model;
        Cart cart=new Cart();
        cart.setUserId(userId).setGameId(addCartParam.getGameId());
        try {
            cartService.addToCart(cart);
        } catch (ServiceException e) {
            model = ResultModel.fail(e.getCode(), e.getMessage());
            throw new ServerException(model, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResultModel.ok();
    }
    @ApiOperation(value="从购物车中移除", httpMethod="DELETE")
    @Authorization
    @DeleteMapping("/removeFromCart/{id}/{userId}")
    public ResultModel removeFromCart(@PathVariable long userId, @PathVariable Long id){
        Cart cart=new Cart();
        cart.setUserId(userId).setId(id);
        cartService.removeFromCart(cart);
        return ResultModel.ok();
    }
    @ApiOperation(value="选中购物车的游戏", httpMethod="PUT")
    @Authorization
    @PutMapping("/selectCart/{userId}")
    public ResultModel selectCart(@PathVariable long userId,@RequestBody CartOperationVo selectCartParam){
        Cart cart=new Cart();
        cart.setUserId(userId).setId(selectCartParam.getId());
        cartService.selectCart(cart);
        return ResultModel.ok();
    }

    @ApiOperation(value="取消选中购物车的游戏", httpMethod="PUT")
    @Authorization
    @PutMapping("/cancelSelect/{userId}")
    public ResultModel cancelSelect(@PathVariable long userId,@RequestBody CartOperationVo selectCartParam){
        Cart cart=new Cart();
        cart.setUserId(userId).setId(selectCartParam.getId());
        cartService.cancelSelect(cart);
        return ResultModel.ok();
    }


    @ApiOperation(value="选中所有", httpMethod="PUT")
    @Authorization
    @PutMapping("/selectAllCart/{userId}")
    public ResultModel selectAllCart(@PathVariable long userId){
        Cart cart=new Cart();
        cart.setUserId(userId);
        cartService.selectAllCart(cart);
        return ResultModel.ok();
    }

    @ApiOperation(value="取消选中所有", httpMethod="PUT")
    @Authorization
    @PutMapping("/cancelSelectAll/{userId}")
    public ResultModel cancelSelectAll(@PathVariable long userId){
        Cart cart=new Cart();
        cart.setUserId(userId);
        cartService.cancelSelectAllCart(cart);
        return ResultModel.ok();
    }

    @ApiOperation(value="查看我的购物车", httpMethod="GET")
    @Authorization
    @GetMapping("/cartList/{userId}")
    public ResultModel<CartListVo> listMyCart(@PathVariable long userId){
        return ResultModel.ok(cartService.listMyCart(userId));
    }

    @ApiOperation(value="检查购物车是否有重复", httpMethod="GET")
    @Authorization
    @GetMapping("/checkCar/{userId}/{gameId}")
    public ResultModel<Cart> checkCar(@PathVariable long userId, @PathVariable long gameId){
        QueryWrapper<Cart> wrapper = new QueryWrapper<Cart>();
        wrapper.eq("game_id", gameId);
        wrapper.eq("user_id", userId);
        Cart haveCart = cartService.getOne(wrapper);
        return ResultModel.ok(haveCart);
    }
}
