package org.steam.core.service;

import org.steam.common.exception.ServiceException;
import org.steam.core.model.entity.Token;

/**
 * Token服务接口
 *
 * @author mingshan
 */
public interface TokenService {

    /**
     * Creates the token of authorization.
     *
     * @param userId
     * @return The model of Token.
     */
    Token creatToken(long userId);

    /**
     * Deteles the token of authorization.
     *
     * @param userId
     */
    void deleteToken(long userId);

    /**
     * Checks out the token that is from Redis.
     *
     * @param token
     * @return
     */
    boolean checkToken(Token token);

    /**
     * Gets the model of Token from authorization string.
     *
     * @param authorization
     * @return The model of Token.
     */
    Token getToken(String authorization);
}
