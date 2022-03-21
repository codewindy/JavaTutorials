package com.codewindy.mongodb.utils;

import com.codewindy.mongodb.service.Caculator;
import com.codewindy.mongodb.service.impl.CaculatorImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author codewindy
 * @date 2021-07-25 4:59 PM
 * @since 1.0.0
 */
public class TestJdkProxy {



    public static void main(String[] args) {

        CaculatorImpl ci = new CaculatorImpl();
        MyInvokeHandler handler = new MyInvokeHandler();
        Object o = Proxy.newProxyInstance(CaculatorImpl.class.getClassLoader(), CaculatorImpl.class.getInterfaces(), handler);
    }
}