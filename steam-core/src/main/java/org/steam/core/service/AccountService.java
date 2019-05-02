package org.steam.core.service;

import org.steam.common.exception.ServiceException;
import org.steam.core.model.entity.Token;
import org.steam.core.model.entity.User;

/**
 * 登录服务
 *
 * @author mingshan
 */
public interface AccountService {

    /**
     * 登录
     *
     * @param email 邮箱
     * @param password 密码
     * @return
     * @throws ServiceException
     */
    Token login(String email, String password) throws ServiceException;

    /**
     * 登出
     *
     * @param user 当前用户
     */
    void logout(User user);

    /**
     * 注册
     *
     * @param user 用户信息
     * @param seccode 验证码
     */
    void register(User user, String seccode) throws ServiceException;
}
