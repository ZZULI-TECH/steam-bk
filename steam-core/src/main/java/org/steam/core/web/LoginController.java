package org.steam.core.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.steam.common.annotation.Authorization;
import org.steam.common.annotation.CurrentUser;
import org.steam.common.constant.Constants;
import org.steam.common.model.ResultModel;
import org.steam.core.model.dto.LoginDTO;
import org.steam.core.model.entity.Token;
import org.steam.core.model.entity.User;
import org.steam.core.model.vo.TokenVO;
import org.steam.core.service.LoginService;

/**
 * The API of token for login and logout.
 *
 * @author mingshan
 */
@Api(value = "tokens")
@RestController
@RequestMapping("/api/tokens")
@Slf4j
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private MapperFacade orikaMapperFacade;

    /**
     * Login
     * @param tokenVO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value="Log in", httpMethod="POST", notes="Login")
    public ResponseEntity<ResultModel> login(@RequestBody TokenVO tokenVO) {
        LoginDTO login = orikaMapperFacade.map(tokenVO, LoginDTO.class);
        Token token = loginService.login(login);
        ResultModel<Token> model = ResultModel.<Token>builder()
                .code(1001L)
                .message(Constants.RESPONSE_OK)
                .content(token)
                .build();

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    /**
     * Logout
     * @param user
     * @return ResultModel
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @Authorization
    @ApiOperation(value="Log out", httpMethod="DELETE", notes="Logout")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "String",
                    paramType = "header")
    })
    public ResponseEntity<ResultModel> logout(@RequestBody @CurrentUser User user) {
        loginService.logout(user);
        ResultModel<Token> model = ResultModel.<Token>builder()
                .code(1002L)
                .message(Constants.RESPONSE_OK)
                .build();
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
