package org.steam.core.web;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.steam.common.annotation.Authorization;
import org.steam.common.exception.ServerException;
import org.steam.common.exception.ServiceException;
import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.Order;
import org.steam.core.model.entity.Token;
import org.steam.core.model.entity.User;
import org.steam.core.model.vo.CreateOrderVO;
import org.steam.core.model.vo.OrderVO;
import org.steam.core.service.IOrderService;
import org.steam.core.util.TokenUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author biao
 * @since 2019-04-28
 */
@Api(value = "order")
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @ApiOperation(value="在购物车页面点击立即下单", httpMethod="POST")
    @Authorization
    @PostMapping("/createOrderFromCart/{userId}")
    public ResultModel createOrderFromCart(@PathVariable long userId){
        ResultModel<Token> model;
        try {
            orderService.createOrderFromCart(userId);
        } catch (ServiceException e) {
            model = ResultModel.fail(e.getCode(), e.getMessage());
            throw new ServerException(model, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResultModel.ok();
    }

    @ApiOperation(value="在游戏页面点击立即下单，即不添加购物车直接下单", httpMethod="POST")
    @Authorization
    @PostMapping("/createOrderQuickly/{userId}")
    public ResultModel createOrderQuickly(@PathVariable long userId,
                                          @RequestBody CreateOrderVO orderVO){
        ResultModel<Token> model;
        try {
            orderService.createOrderQuickly(userId,orderVO.getGameId(),orderVO.getNum());
        } catch (ServiceException e) {
            model = ResultModel.fail(e.getCode(), e.getMessage());
            throw new ServerException(model, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResultModel.ok();
    }

    @ApiOperation(value="取消未付款的订单", httpMethod="POST")
    @Authorization
    @PostMapping("/cancelOrder/{userId}")
    public ResultModel cancelOrder(@PathVariable long userId,
                                   @RequestBody OrderVO orderVO){
        ResultModel<Token> model;
        try {
            orderService.cancelOrder(orderVO.getId(),userId);
        } catch (ServiceException e) {
            model = ResultModel.fail(e.getCode(), e.getMessage());
            throw new ServerException(model, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResultModel.ok();
    }

    @ApiOperation(value="订单列表", httpMethod="GET")
    @Authorization
    @GetMapping("/orderList/{userId}/{pageNum}/{pageSize}/{orderStatus}")
    public ResponseEntity<ResultModel> orderList(@PathVariable long userId,
                                                   @PathVariable Integer pageNum ,
                                                   @PathVariable Integer pageSize,
                                                   @PathVariable(required = false) Integer orderStatus){
        ResultModel<Page> model;
        Order order=new Order();
        order.setUid(userId).setOrderStatus(orderStatus);
        model = ResultModel.ok(orderService.orderList(pageNum,pageSize,order));
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @ApiOperation(value="订单详情", httpMethod="GET")
    @Authorization
    @GetMapping("/{userId}/{id}")
    public ResponseEntity<ResultModel> orderList(@PathVariable long userId,
                                                 @PathVariable Long id ){
        ResultModel<Order> model;
        model = ResultModel.ok(orderService.orderInfo(id));
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

}
