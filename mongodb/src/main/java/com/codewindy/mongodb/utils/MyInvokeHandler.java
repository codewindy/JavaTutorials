package com.codewindy.mongodb.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author codewindy
 * @date 2021-07-25 5:54 PM
 * @since 1.0.0
 */
public class MyInvokeHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy  before = " + Arrays.asList(args));
        Object invoke = method.invoke(proxy, args);
        System.out.println("invoke = " + invoke);
        System.out.println("proxy  after = " + Arrays.asList(args));

        return null;
    }
}