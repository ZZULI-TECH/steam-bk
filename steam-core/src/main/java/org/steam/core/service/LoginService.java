package org.steam.core.service;

import org.steam.common.exception.ServiceException;
import org.steam.core.model.dto.Login;
import org.steam.core.model.entity.Token;
import org.steam.core.model.entity.User;

/**
 * 登录服务
 *
 * @author mingshan
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param login 登录信息
     * @return token
     * @throws ServiceException
     */
    Token login(Login login) throws ServiceException;

    /**
     * 登出
     *
     * @param user 当前用户
     */
    void logout(User user);
}
