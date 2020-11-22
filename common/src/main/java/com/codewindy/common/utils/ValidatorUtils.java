package com.codewindy.common.utils;

import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @Desc 添加实体类的参数完整性校验
 * @Author jkwindy
 * @date 2018-12-26 23:10
 */
public class ValidatorUtils {
    private final static Logger logger= LoggerFactory.getLogger(ValidatorUtils.class);
    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
    public static  <T>boolean validate(T domain){
        Set<ConstraintViolation<T>> validateResult = validator.validate(domain);
        //异常set里面有值,遍历输出
        if (validateResult.size()>0){
            logger.info("validateResult===================={}",validateResult.iterator().next().getMessage()+".校验异常");
            return  false;
        }
        return true;
    }
}
