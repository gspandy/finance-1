//package com.easyrong.wireless.financegateway;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Component
//public class Gatewayfilter extends ZuulFilter {
//    public static final Logger logger = LogManager.getLogger(Gatewayfilter.class.getName());
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        logger.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
//
//        Object grant_type = request.getParameter("grant_type");
//        if (null != grant_type) {
//            return null;
//        }
//
//        Object accessToken = request.getParameter("access_token");
//        if(accessToken == null) {
//            logger.warn("access_token is empty");
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            try {
//                ctx.getResponse().getWriter().write("access_token is empty");
//            }catch (Exception e){}
//
//            return null;
//        }
//        logger.info("ok");
//        return null;
//    }
//}
