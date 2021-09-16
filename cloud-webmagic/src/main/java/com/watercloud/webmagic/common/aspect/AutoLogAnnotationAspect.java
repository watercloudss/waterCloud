package com.watercloud.webmagic.common.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.watercloud.webmagic.common.aspect.annotation.AutoLogAnnotation;
import com.watercloud.webmagic.common.util.CommonConstant;
import com.watercloud.webmagic.common.util.IPUtils;
import com.watercloud.webmagic.common.vo.Result;
import com.watercloud.webmagic.entity.SysLog;
import com.watercloud.webmagic.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;

@Aspect
@Component
public class AutoLogAnnotationAspect {
    @Autowired
    private ISysLogService iSysLogService;

    @Pointcut("@annotation(com.watercloud.webmagic.common.aspect.annotation.AutoLogAnnotation)")
    public void logPointCut() {}

    //环绕通知
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        this.saveLog(point,time,result);
        return result;
    }

    //异常通知
    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void afterThrowing(JoinPoint point, Throwable e){
        MethodSignature signature = (MethodSignature) point.getSignature();
        this.saveLog(point,-1,e.getMessage());
    }

    private void saveLog(JoinPoint point,long time,Object result){
        SysLog sysLog = new SysLog();
        if(time == -1){
            sysLog.setRequestStatus(CommonConstant.SC_INTERNAL_SERVER_ERROR_500.toString());
        }
        if (Result.class.isInstance(result)){
            Result res = (Result)result;
            if(CommonConstant.SC_OK_200.equals(res.getCode())){
                sysLog.setRequestStatus(CommonConstant.SC_OK_200.toString());
            }else{
                sysLog.setRequestStatus(CommonConstant.SC_INTERNAL_SERVER_ERROR_500.toString());
            }
            sysLog.setResponseParam(JSON.toJSONString(res));
        }else{
            sysLog.setResponseParam(result.toString());
        }
        sysLog.setCostTime(time);
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        AutoLogAnnotation autoLogAnnotation = method.getAnnotation(AutoLogAnnotation.class);
        if(autoLogAnnotation!=null){
            int logType = autoLogAnnotation.logType();
            sysLog.setLogType(logType);
        }

        String clazzName = point.getTarget().getClass().getName();
        String methodName = method.getName();
        sysLog.setMethodName(methodName);
        sysLog.setMethodClass(clazzName+"."+methodName+"()");

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        sysLog.setRequestParam(getReqestParams(request,point));
        sysLog.setIp(IPUtils.getIpAddr(request));

        //保存日志
        CompletableFuture.runAsync(()->{
            iSysLogService.save(sysLog);
        });
    }
    /**
     * @Description: 获取请求参数
     * @param request:  request
     * @param joinPoint:  joinPoint
     * @Return: java.lang.String
     */
    private String getReqestParams(HttpServletRequest request, JoinPoint joinPoint) {
        String httpMethod = request.getMethod();
        String params = "";
        if ("POST".equals(httpMethod) || "PUT".equals(httpMethod) || "PATCH".equals(httpMethod)) {
            Object[] paramsArray = joinPoint.getArgs();
            // java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
            //  https://my.oschina.net/mengzhang6/blog/2395893
            Object[] arguments  = new Object[paramsArray.length];
            for (int i = 0; i < paramsArray.length; i++) {
                if (paramsArray[i] instanceof ServletRequest || paramsArray[i] instanceof ServletResponse || paramsArray[i] instanceof MultipartFile) {
                    //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                    //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                    continue;
                }
                arguments[i] = paramsArray[i];
            }
            //update-begin-author:taoyan date:20200724 for:日志数据太长的直接过滤掉
            PropertyFilter profilter = new PropertyFilter() {
                @Override
                public boolean apply(Object o, String name, Object value) {
                    if(value!=null && value.toString().length()>500){
                        return false;
                    }
                    return true;
                }
            };
            params = JSONObject.toJSONString(arguments, profilter);
            //update-end-author:taoyan date:20200724 for:日志数据太长的直接过滤掉
        } else {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            // 请求的方法参数值
            Object[] args = joinPoint.getArgs();
            // 请求的方法参数名称
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            String[] paramNames = u.getParameterNames(method);
            if (args != null && paramNames != null) {
                for (int i = 0; i < args.length; i++) {
                    params += "  " + paramNames[i] + ": " + args[i];
                }
            }
        }
        return params;
    }
}
