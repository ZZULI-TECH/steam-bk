package org.steam.core.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.steam.common.annotation.Authorization;
import org.steam.common.exception.ServerException;
import org.steam.common.exception.ServiceException;
import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.Token;
import org.steam.core.model.entity.User;
import org.steam.core.model.vo.PayVo;
import org.steam.core.service.IPayService;
import org.steam.core.util.TokenUtil;

@RestController
@RequestMapping("/api/pay")
public class PayController {

    @Autowired
    private IPayService payService;

    @ApiOperation(value="支付订单", httpMethod="POST")
    @Authorization
    @PostMapping("/payOrder")
    public ResultModel payOrder(@RequestHeader(name = "authorization") String token, @RequestBody PayVo payVo){
        User user = TokenUtil.getUserFromToken(token);
        ResultModel<Token> model;
        if(user == null ){
            throw new ServerException();
        }
        try {
            payService.pay(payVo.getOrderId(),user.getId());
        } catch (ServiceException e) {
            model = ResultModel.fail(e.getCode(), e.getMessage());
            throw new ServerException(model, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResultModel.ok();
    }

}