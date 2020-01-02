package com.codewindy.mongodb.service;


import com.codewindy.mongodb.service.impl.WeatherServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * @author jkwindy@126.com
 * @date 2019-12-15 20:51
 */
public class WeatherTest {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:8080/getWeatherInfo",new WeatherServiceImpl());
    }
}
