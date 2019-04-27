package org.steam.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.steam.common.cache.RedisCache;
import org.steam.common.constant.Constants;
import org.steam.common.exception.ServiceException;
import org.steam.core.model.entity.Token;
import org.steam.core.model.entity.User;
import org.steam.core.service.TokenService;
import org.steam.core.util.JWTUtil;
import org.steam.core.util.TokenUtil;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author mingshan
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private RedisCache redisCache;

    @Override
    public Token creatToken(long userId) throws ServiceException {
        User user = new User();
        user.setId(userId);
        String subject = JWTUtil.generalSubject(user);
        String token = JWTUtil.createJWT(userId, subject, Constants.JWT_TTL);
        Token model = new Token(userId, token);

        log.info("Create token, userId: {}", userId);
        redisCache.setEx(String.valueOf(userId), token, Constants.TOKEN_EXPIRES_HOUR * 3600, TimeUnit.MINUTES);
        return model;
    }

    @Override
    public void deleteToken(long userId) {
        boolean broken = false;

        log.info("Delete token, userId: {}", userId);
        redisCache.delete(String.valueOf(userId));
    }

    @Override
    public boolean checkToken(Token model) {
        Objects.requireNonNull(model);

        log.info("Check token, userId: {}", model.getUserId());
        String source = redisCache.get(String.valueOf(model.getUserId()));
        if (StringUtils.isEmpty(source) || !source.equals(model.getToken())) {
            return false;
        }
        // 延长过期时间
        redisCache.expire(String.valueOf(model.getUserId()), Constants.TOKEN_EXPIRES_HOUR * 3600, TimeUnit.MINUTES);

        return true;
    }

    @Override
    public Token getToken(String authorization) {
        if (authorization == null || "".equals(authorization)) {
            return null;
        }

        User user = TokenUtil.getUserFromToken(authorization);
        if (user == null) {
            return null;
        }

        long userId = user.getId();

        String token = TokenUtil.extractJwtTokenFromAuthorizationHeader(authorization);
        Token model = new Token(userId, token);
        return model;
    }
}
