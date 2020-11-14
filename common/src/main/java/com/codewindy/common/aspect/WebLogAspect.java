package com.codewindy.common.aspect;

import cn.hutool.core.map.MapUtil;
import com.codewindy.common.vo.WebLogVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @author codewindy
 * @date 2020-11-14 10:39 AM
 * @since 1.0.0
 */
@Slf4j
@Component
@Aspect
public class WebLogAspect {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long beginTime = System.currentTimeMillis();
        WebLogVO vo = new WebLogVO();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation annotation = method.getAnnotation(ApiOperation.class);
            String value = annotation.value();
            vo.setDesc(value);
        }
        vo.setMethod(method.getName());

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Enumeration<String> enumeration = request.getParameterNames();
        HashMap<String, Object> paramsMap = MapUtil.newHashMap(16);
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            paramsMap.put(name, request.getParameter(name));
        }
        vo.setParams(paramsMap);
        log.info("[入参== {} ]", vo);
        Object result = null;
        try {
            result = joinPoint.proceed();
        } finally {
            log.info("[接口 {} 返回结果result=== {} , spendTime== {} ]", vo.getMethod(), result, System.currentTimeMillis() - beginTime);
        }
    }
}