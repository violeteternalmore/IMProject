package org.example.backend.interceptor;

import org.example.backend.context.UserContext;
import org.example.backend.exception.BusinessException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String LOGIN_KEY_PREFIX = "login:token:";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object handler) {
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null || token.isBlank()) {
            throw new BusinessException(400, "未登录");
        }

        String redisToken = LOGIN_KEY_PREFIX + token;
        String userID = stringRedisTemplate.opsForValue().get(redisToken);
        if (userID == null) {
            throw new BusinessException(400, "登录已过期");
        }

        UserContext.set(Long.valueOf(userID));

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object handler, Exception ex) {
        UserContext.remove();
    }

}
