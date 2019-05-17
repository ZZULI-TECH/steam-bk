package org.steam.core.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
import org.steam.common.exception.VersionException;
import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.Order;
import org.steam.core.model.entity.Token;
import org.steam.core.model.entity.User;
import org.steam.core.model.vo.CreateOrderVO;
import org.steam.core.model.vo.OrderVO;
import org.steam.core.service.IOrderService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
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
            Long  orderFromCart= orderService.createOrderFromCart(userId);
            return ResultModel.ok(orderFromCart);
        } catch (ServiceException e) {
            model = ResultModel.fail(e.getCode(), e.getMessage());
            throw new ServerException(model, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value="在游戏页面点击立即下单，即不添加购物车直接下单", httpMethod="POST")
    @Authorization
    @PostMapping("/createOrderQuickly/{userId}")
    public ResultModel createOrderQuickly(@PathVariable long userId,
                                          @RequestBody CreateOrderVO orderVO){
        ResultModel<Token> model;
        try {
            Long orderQuickly = orderService.createOrderQuickly(userId, orderVO.getGameId(), orderVO.getNum());
            return ResultModel.ok(orderQuickly);
        } catch (ServiceException e) {
            model = ResultModel.fail(e.getCode(), e.getMessage());
            throw new ServerException(model, HttpStatus.INTERNAL_SERVER_ERROR);
        }

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
                                                   @PathVariable(required = false) String orderStatus){
        ResultModel<Page> model;
        Order order=new Order();
        if (!"null".equals(orderStatus) && orderStatus != null){
            order.setUid(userId).setOrderStatus(Integer.parseInt(orderStatus));
        } else {
            order.setUid(userId);
        }

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

    @ApiOperation(value="查询所有，带分页", httpMethod="GET")
    @GetMapping("/list")
    public ResultModel<IPage> getAllOrderList(Integer pageSize, Integer pageNum, Order order) {
        Page<Order> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        QueryWrapper<Order> wrapper = new QueryWrapper<>();

        IPage<Order> orders =  orderService.page(page, wrapper);
        return ResultModel.ok(orders);
    }

    @ApiOperation(value="更新信息", httpMethod="PUT")
    @PutMapping
    public ResultModel<User> updateById(@RequestBody Order order){
        orderService.updateById(order);
        return ResultModel.ok();
    }

    @ApiOperation(value="删除", httpMethod="DELETE")
    @DeleteMapping("/{id}")
    public ResultModel<User> delete(@PathVariable Long id){
        orderService.removeById(id);
        return ResultModel.ok();
    }

}
