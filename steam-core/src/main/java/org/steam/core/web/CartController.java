package org.steam.core.web;


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
    @PostMapping("/addToCart")
    public ResultModel addToCart(@RequestHeader(name = "authorization") String token, @RequestBody AddCartVo addCartParam){
        User user = TokenUtil.getUserFromToken(token);
        ResultModel<Token> model;
        if(user == null ){
            throw new ServerException();
        }
        Cart cart=new Cart();
        cart.setUserId(user.getId()).setGameId(addCartParam.getGameId());
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
    @DeleteMapping("/removeFromCart/{id}")
    public ResultModel removeFromCart(@RequestHeader(name = "authorization") String token, @PathVariable Long id){
        User user = TokenUtil.getUserFromToken(token);
        if(user == null ){
            throw new ServerException();
        }
        Cart cart=new Cart();
        cart.setUserId(user.getId()).setId(id);
        cartService.removeFromCart(cart);
        return ResultModel.ok();
    }
    @ApiOperation(value="选中购物车的游戏", httpMethod="PUT")
    @Authorization
    @PutMapping("/selectCart")
    public ResultModel selectCart(@RequestHeader(name = "authorization") String token,@RequestBody CartOperationVo selectCartParam){
        User user = TokenUtil.getUserFromToken(token);
        if(user == null ){
            throw new ServerException();
        }
        Cart cart=new Cart();
        cart.setUserId(user.getId()).setId(selectCartParam.getId());
        cartService.selectCart(cart);
        return ResultModel.ok();
    }

    @ApiOperation(value="取消选中购物车的游戏", httpMethod="PUT")
    @Authorization
    @PutMapping("/cancelSelect")
    public ResultModel cancelSelect(@RequestHeader(name = "authorization") String token,@RequestBody CartOperationVo selectCartParam){
        User user = TokenUtil.getUserFromToken(token);
        if(user == null ){
            throw new ServerException();
        }
        Cart cart=new Cart();
        cart.setUserId(user.getId()).setId(selectCartParam.getId());
        cartService.cancelSelect(cart);
        return ResultModel.ok();
    }


    @ApiOperation(value="选中所有", httpMethod="PUT")
    @Authorization
    @PutMapping("/selectAllCart")
    public ResultModel selectAllCart(@RequestHeader(name = "authorization") String token){
        User user = TokenUtil.getUserFromToken(token);
        if(user == null ){
            throw new ServerException();
        }
        Cart cart=new Cart();
        cart.setUserId(user.getId());
        cartService.selectAllCart(cart);
        return ResultModel.ok();
    }

    @ApiOperation(value="取消选中所有", httpMethod="PUT")
    @Authorization
    @PutMapping("/cancelSelectAll")
    public ResultModel cancelSelectAll(@RequestHeader(name = "authorization") String token){
        User user = TokenUtil.getUserFromToken(token);
        if(user == null ){
            throw new ServerException();
        }
        Cart cart=new Cart();
        cart.setUserId(user.getId());
        cartService.cancelSelectAllCart(cart);
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
