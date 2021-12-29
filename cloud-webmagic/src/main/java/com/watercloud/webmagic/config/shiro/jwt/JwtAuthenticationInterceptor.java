package com.watercloud.webmagic.config.shiro.jwt;

import com.alibaba.fastjson.JSON;
import com.cloudwater.common.commonVo.Result;
import com.watercloud.webmagic.common.util.CommonConstant;
import com.watercloud.webmagic.common.aspect.annotation.AuthCheckAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Slf4j
public class JwtAuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(AuthCheckAnnotation.class)) {
//            AuthCheckAnnotation userLoginToken = method.getAnnotation(AuthCheckAnnotation.class);
            if(StringUtils.isEmpty(token)){
                authCheckResultWriter(httpServletResponse,"需要验证！");
                return false;
            }
//            Map<String,String> checkReult = JwtTool.check(token);
//            String isSuccess = checkReult.get("isSuccess");
//            if(isSuccess.equals("0")){
//                authCheckResultWriter(httpServletResponse,checkReult.get("msg"));
//                return false;
//            }
        }
        return true;
    }

    private static void authCheckResultWriter(HttpServletResponse httpServletResponse,String msg) throws Exception{
        Result<Object> result = Result.error(CommonConstant.SC_NO_AUTHZ,msg);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.append(JSON.toJSON(result).toString());
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
