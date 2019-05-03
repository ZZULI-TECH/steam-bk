package org.steam.core.web;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.steam.common.annotation.Authorization;
import org.steam.common.exception.ServerException;
import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.Cart;
import org.steam.core.model.entity.User;
import org.steam.core.model.vo.CartListVo;
import org.steam.core.service.ICartService;
import org.steam.core.util.TokenUtil;

import javax.validation.Valid;

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

    @ApiOperation(value="添加到购物车", httpMethod="PUT")
    @Authorization
    @PutMapping("/addToCart")
    public ResultModel addToCart(@RequestHeader(name = "authorization") String token,@RequestBody @Valid Cart cart){
        User user = TokenUtil.getUserFromToken(token);
        if(user == null ){
            throw new ServerException();
        }
        cart.setUserId(cart.getUserId());
        cartService.addToCart(cart);
        return ResultModel.ok();
    }
    @ApiOperation(value="从购物车中移除", httpMethod="PUT")
    @Authorization
    @PutMapping("/removeFromCart")
    public ResultModel removeFromCart(@RequestHeader(name = "authorization") String token,@RequestBody @Valid Cart cart){
        User user = TokenUtil.getUserFromToken(token);
        if(user == null ){
            throw new ServerException();
        }
        cart.setUserId(cart.getUserId());
        cartService.removeFromCart(cart);
        return ResultModel.ok();
    }
    @ApiOperation(value="选中购物车的游戏", httpMethod="PUT")
    @Authorization
    @PutMapping("/selectCart")
    public ResultModel selectCart(@RequestHeader(name = "authorization") String token,@RequestBody @Valid Cart cart){
        User user = TokenUtil.getUserFromToken(token);
        if(user == null ){
            throw new ServerException();
        }
        cart.setUserId(cart.getUserId());
        cartService.selectCart(cart);
        return ResultModel.ok();
    }
    @ApiOperation(value="选中所有", httpMethod="PUT")
    @Authorization
    @PutMapping("/selectAllCart")
    public ResultModel selectAllCart(@RequestHeader(name = "authorization") String token,@RequestBody @Valid Cart cart){
        User user = TokenUtil.getUserFromToken(token);
        if(user == null ){
            throw new ServerException();
        }
        cart.setUserId(cart.getUserId());
        cartService.selectAllCart(cart);
        return ResultModel.ok();
    }
    @ApiOperation(value="查看我的购物车", httpMethod="GET")
    @Authorization
    @GetMapping("/cartList")
    public ResultModel<CartListVo> listMyCart(@RequestHeader(name = "authorization") String token){
        User user = TokenUtil.getUserFromToken(token);
        if(user == null ){
            throw new ServerException();
        }
        return ResultModel.ok(cartService.listMyCart(user.getId()));
    }



}
