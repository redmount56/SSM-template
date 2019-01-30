package com.redmount.template.configurer;

import com.redmount.template.core.annotation.Token;
import com.redmount.template.core.exception.AuthorizationException;
import com.redmount.template.util.JwtUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * 方法拦截器
 * Token鉴权
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有Token注解，没有则跳过认证
        if (method.isAnnotationPresent(Token.class)) {
            Token loginToken = method.getAnnotation(Token.class);
            if (!loginToken.value()) {
                return true;
            }
        } else {
            return true;
        }
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        if (!JwtUtil.isVerify(token)) {
            throw new AuthorizationException();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
