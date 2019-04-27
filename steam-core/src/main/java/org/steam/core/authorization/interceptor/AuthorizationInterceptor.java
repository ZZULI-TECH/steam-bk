package org.steam.core.authorization.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.steam.common.annotation.Authorization;
import org.steam.common.constant.Constants;
import org.steam.core.model.entity.Token;
import org.steam.core.service.TokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * The custom interceptor that checks out the current request has authorization.
 *
 * @author: mingshan
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenService tokenServicer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // Checks out the annotation of authorization that is method level.
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        if (method.getAnnotation(Authorization.class) == null) {
            return true;
        }

        // Gets authorization string from request header.
        String authorization = request.getHeader(Constants.AUTHORIZATION);

        // Gets the model of Token from authorization string.
        Token token = tokenServicer.getToken(authorization);
        // Checks out the token that is from Redis.
        if (tokenServicer.checkToken(token)) {
            // Puts userId into request.
            request.setAttribute(Constants.CURRENT_USER_ID, token.getUserId());
            return true;
        } else {
            // If verify token failed, and the current method has the annotation of authorization,
            // sets the response code to 401.
            // The 401 code means unauthorized.
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
