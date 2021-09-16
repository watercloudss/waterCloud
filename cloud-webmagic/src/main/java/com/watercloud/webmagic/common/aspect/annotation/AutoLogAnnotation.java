package com.watercloud.webmagic.common.aspect.annotation;

import com.watercloud.webmagic.common.util.CommonConstant;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoLogAnnotation {
    /**
     * 日志内容
     *
     * @return
     */
    String code() default "";

    /**
     * 日志类型
     *
     * @return 1:操作日志;2:登录日志;3:定时任务;
     */
    int logType() default CommonConstant.LOG_TYPE_1;
}
