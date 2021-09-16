package com.watercloud.webmagic.common.validator.annotation;

import com.watercloud.webmagic.common.validator.validatorImpl.GenderValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
@Documented
public @interface Gender {
    String message() default "性别为男或者女";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};
}
