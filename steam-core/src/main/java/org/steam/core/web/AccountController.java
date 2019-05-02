package org.steam.core.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.steam.common.annotation.Authorization;
import org.steam.common.annotation.CurrentUser;
import org.steam.common.exception.ServerException;
import org.steam.common.exception.ServiceException;
import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.Token;
import org.steam.core.model.entity.User;
import org.steam.core.model.vo.RegisterVO;
import org.steam.core.model.vo.TokenVO;
import org.steam.core.service.AccountService;
import org.steam.core.service.VerificationCodeService;

/**
 * 帐户
 *
 * @author mingshan
 */
@Api(value = "account")
@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MapperFacade orikaMapperFacade;

    @Autowired
    private VerificationCodeService verificationCodeService;

    /**
     * 登录
     *
     * @param tokenVO
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value="Login", httpMethod="POST", notes="Login")
    public ResponseEntity<ResultModel> login(@RequestBody TokenVO tokenVO) {
        Token token;
        ResultModel<Token> model;
        try {
            token = accountService.login(tokenVO.getEmail(), tokenVO.getPassword());
            model = ResultModel.ok(token);
        } catch (ServiceException e) {
            model = ResultModel.fail(e.getCode(), e.getMessage());
            throw new ServerException(model, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    /**
     * 登出
     *
     * @param user
     * @return ResultModel
     */
    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    @Authorization
    @ApiOperation(value="Logout", httpMethod="DELETE", notes="Logout")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "String",
                    paramType = "header")
    })
    public ResponseEntity<ResultModel> logout(@RequestBody @CurrentUser User user) {
        accountService.logout(user);

        return new ResponseEntity<>(ResultModel.ok(), HttpStatus.OK);
    }

    /**
     * 注册
     *
     * @param registerVO
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value="Register", httpMethod="POST", notes="注册")
    public ResponseEntity<ResultModel> register(@RequestBody RegisterVO registerVO) {
        User user = orikaMapperFacade.map(registerVO, User.class);
        ResultModel model;
        try {
            accountService.register(user, registerVO.getSeccode());
            model = ResultModel.ok();
        } catch (ServiceException e) {
            model = ResultModel.fail(e.getCode(), e.getMessage());
            throw new ServerException(model, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @RequestMapping(value = "/getSeccode", method = RequestMethod.GET)
    @ApiOperation(value="Register", httpMethod="GET", notes="获取验证码")
    public ResponseEntity<ResultModel> getSeccode(@RequestParam("email") String email) {
        ResultModel model;
        try {
            verificationCodeService.sendSeccode(email);
            model = ResultModel.ok();
        } catch (ServiceException e) {
            model = ResultModel.fail(e.getCode(), e.getMessage());
            throw new ServerException(model, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
