package org.steam.core.web;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.jpay.alipay.AliPayApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.steam.common.annotation.Authorization;
import org.steam.common.exception.ServerException;
import org.steam.common.exception.ServiceException;
import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.AliPayBean;
import org.steam.core.model.entity.Token;
import org.steam.core.model.entity.User;
import org.steam.core.model.vo.PayPageVO;
import org.steam.core.model.vo.PayVo;
import org.steam.core.service.IPayService;
import org.steam.core.util.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Api(value = "pay")
@RestController
@RequestMapping("/api/pay")
public class PayController {

    @Autowired
    private AliPayBean aliPayBean;
    @Autowired
    private IPayService payService;

    @ApiOperation(value="支付订单", httpMethod="POST")
    @Authorization
    @PostMapping("/payOrder/{userId}")
    public ResultModel payOrder(@PathVariable long userId, @RequestBody PayVo payVo){
        ResultModel<Token> model;
        try {
            payService.pay(payVo.getOrderId(),userId);
        } catch (ServiceException e) {
            model = ResultModel.fail(e.getCode(), e.getMessage());
            throw new ServerException(model, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResultModel.ok();
    }



    @ApiOperation(value="支付订单", httpMethod="POST")
    @RequestMapping("/notify")
    public String payNotify(HttpServletRequest request){
        try {
            // 获取支付宝反馈信息
            Map<String, String> params = AliPayApi.toMap(request);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
            boolean verify_result = AlipaySignature.rsaCheckV1(params, aliPayBean.getPublicKey(), "UTF-8", "RSA2");
            if (verify_result) {// 验证成功
                try {
                    payService.payCallBack(params);
                } catch (ServiceException e) {
                    return "failure";
                }
                return "success";
            } else {
                return "failure";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "failure";
        }

    }

}
