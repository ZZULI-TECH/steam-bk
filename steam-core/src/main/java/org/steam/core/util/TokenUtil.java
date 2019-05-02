package org.steam.core.util;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.steam.common.constant.Constants;
import org.steam.common.util.JsonUtil;
import org.steam.core.model.entity.User;

/**
 * Token 工具类
 *
 * @author mingshan
 */
@Slf4j
public class TokenUtil {

    /**
     * 从token中解析出用户信息
     *
     * @param auth token信息
     * @return 用户信息
     */
    public static User getUserFromToken(String auth) {
        if (auth == null) {
            return null;
        }

        if (!Constants.TOKEN_PREFIX.equals(auth.substring(0, 7))) {
            return null;
        } else {
            String token = extractJwtTokenFromAuthorizationHeader(auth);
            try {
                Claims claims = JWTUtil.parseJWT(token);
                String subject = claims.getSubject();
                User user = JsonUtil.jsonToObject(subject, User.class);
                return user;
            } catch (Exception e) {
                log.error("解析JWT token 失败！");
            }
        }
        return null;
    }

    /**
     * 获取真实的toekn，去掉‘Bearer ’
     *
     * @param auth token
     * @return 真实token
     */
    public static String extractJwtTokenFromAuthorizationHeader(String auth) {
        // Replace "Bearer Token" to "Token" directly
        return auth.replaceFirst("[B|b][E|e][A|a][R|r][E|e][R|r] ", "")
                .replace(" ", "");
    }
}
