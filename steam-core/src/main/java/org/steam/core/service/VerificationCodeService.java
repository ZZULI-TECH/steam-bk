package org.steam.core.service;

import org.steam.common.exception.ServiceException;

/**
 * 验证码服务
 *
 * @author mingshan
 */
public interface VerificationCodeService {

    /**
     * 发送验证码
     *
     * @param email 邮箱
     * @throws ServiceException
     */
    void sendSeccode(String email) throws ServiceException;

    /**
     * 校验验证码
     *
     * @param email 邮箱
     * @param seccode 验证码
     * @throws ServiceException
     */
    void verfiySecCode(String email, String seccode) throws ServiceException;
}
