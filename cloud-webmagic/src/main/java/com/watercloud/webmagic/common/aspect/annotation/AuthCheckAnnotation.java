package com.watercloud.webmagic.common.aspect.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthCheckAnnotation {
    String value() default "";
}
