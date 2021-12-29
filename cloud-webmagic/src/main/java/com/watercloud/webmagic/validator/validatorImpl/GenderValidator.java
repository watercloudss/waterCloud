package com.watercloud.webmagic.validator.validatorImpl;

import com.watercloud.webmagic.validator.annotation.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<Gender, String> {

    // 初始化校验值
    @Override
    public void initialize(Gender constraintAnnotation) {

    }

    // 校验规则
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return "M".equals(value) || "F".equals(value);
    }

}
