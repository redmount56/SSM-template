package com.redmount.template.core.aspect;

import com.redmount.template.util.RequestUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

@Aspect
@Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 定义一个切入点.
     * ~第一个 *代表任意修饰符及任意返回值.
     * ~第二个 *任意包名
     * ~第三个 *代表任意方法.
     * ~第四个 *定义在web包或者子包
     * ~第五个 *任意方法
     * ~ ..匹配任意数量的参数.
     */

    @Pointcut("(execution(public * com.redmount.template.controller.*.*(..))) || (execution(public * com.redmount.template.core.Controller.*(..)))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        logger.info("-----------------------请求开始" + startTime + "-----------------------");
        logger.info("WebLogAspect.doBefore()");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        logger.info("请求地址 : " + request.getRequestURL().toString());
        logger.info("请求方式 : " + request.getMethod());
        logger.info("Header:" + RequestUtil.getHeaderStringFromRequest(request));
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("对应处理方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("参数 : \r\n" + Arrays.toString(joinPoint.getArgs()));
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = enu.nextElement();
            logger.info(paraName + " : " + request.getParameter(paraName));
        }
    }

    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint) {
        // 处理完请求，返回内容
        logger.info("WebLogAspect.doAfterReturning()");
        logger.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
        logger.info("-----------------------请求结束" + startTime + "-----------------------");
    }
}
