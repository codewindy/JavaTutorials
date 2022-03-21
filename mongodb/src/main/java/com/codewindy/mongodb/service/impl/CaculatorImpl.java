package com.codewindy.mongodb.service.impl;

import com.codewindy.mongodb.service.Caculator;

/**
 * @author codewindy
 * @date 2021-07-25 4:51 PM
 * @since 1.0.0
 */
public class CaculatorImpl  implements Caculator {


    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }

    @Override
    public int multiply(int a, int b) {
        return  a * b;
    }

    @Override
    public int divide(int a, int b) {
        return a / b;
    }
}