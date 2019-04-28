package org.steam.core.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.steam.common.exception.ParameterException;
import org.steam.common.exception.ServiceException;
import org.steam.common.model.ResultModel;
import org.steam.common.util.MD5Util;
import org.steam.core.model.dto.Login;
import org.steam.core.model.entity.Token;
import org.steam.core.model.entity.User;
import org.steam.core.service.LoginService;
import org.steam.core.service.TokenService;
import org.steam.core.service.UserService;

/**
 * @author mingshan
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Override
    public Token login(Login login) {
        String email = login.getEmail();
        String password = login.getPassword();

        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new ParameterException("UserName or password is empty.");
        }

        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, email));

        String newPassword = MD5Util.md5(password, email);
        if (user == null || !newPassword.equals(user.getPassword())) {
            ResultModel<Token> result = ResultModel.<Token>builder()
                    .code(1003L)
                    .message("UserName or password is incorrect.")
                    .build();

            log.info("UserName or password is incorrect.");
            throw new ServiceException(result, HttpStatus.OK);
        }

        Token token = tokenService.creatToken(user.getId());
        return token;
    }

    @Override
    public void logout(User user) {
        tokenService.deleteToken(user.getId());
    }
}
