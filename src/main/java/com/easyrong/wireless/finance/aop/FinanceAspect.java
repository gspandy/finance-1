package com.easyrong.wireless.finance.aop;

import com.alibaba.fastjson.JSON;
import com.easyrong.wireless.finance.base.BaseRspEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Aspect
@Order(5)
@Component
public class FinanceAspect {

    private Logger logger = LoggerFactory.getLogger(FinanceAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.easyrong.wireless.finance.controller..*.*(..))")
    public void controllerPointcut() {
    }

    @Pointcut("execution(public * com.easyrong.wireless.finance.controller.UserController.*(..))")
    public void userPointcut() {
        //用户登录、注册、登出功能不需要Session验证
    }

    @Pointcut("controllerPointcut()&&(!userPointcut())")
    public void sessionTimeOutPointcut() {
    }

    @Around("sessionTimeOutPointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) {
        startTime.set(System.currentTimeMillis());
        Object result = null;
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        HttpSession session = request.getSession();
        if (session.getAttribute("loginname") != null) {
            try {
                result = pjp.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return result;
        } else {
            result = new ModelAndView("login");
        }
        return result;
    }

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("请求地址 : " + request.getRequestURL().toString());
        logger.info("请求方法 : " + request.getMethod());
        logger.info("IP地址 : " + request.getRemoteAddr());
        logger.info("处理接口 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        if ("POST".equals(request.getMethod())) {
            logger.info("请求参数 : " + JSON.toJSONString(joinPoint.getArgs()));
        } else {
            logger.info("请求参数 : " + request.getQueryString());
        }

    }

    @AfterReturning(returning = "ret", pointcut = "controllerPointcut()")
    public void doAfterReturning(Object ret) throws Throwable {
        logger.info("返回内容 : " + ret);
        logger.info("消耗时间 : " + (System.currentTimeMillis() - startTime.get()) + "毫秒");
    }
}
