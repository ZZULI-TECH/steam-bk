package org.steam.core.authorization.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.steam.common.annotation.CurrentUser;
import org.steam.common.constant.Constants;
import org.steam.core.model.entity.User;
import org.steam.core.service.UserService;

/**
 *
 *
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(User.class)
                && methodParameter.hasParameterAnnotation(CurrentUser.class);

    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory)
            throws Exception {
        Object source = nativeWebRequest.getAttribute(Constants.CURRENT_USER_ID,
                            RequestAttributes.SCOPE_REQUEST);
        if (source == null) {
            return null;
        }

        long currentUserId = (long)source;
        if (currentUserId != 0) {
            return userService.getById(currentUserId);
        }
        // 400 - Bad Request
        throw new MissingServletRequestPartException(Constants.CURRENT_USER_ID);
    }
}
