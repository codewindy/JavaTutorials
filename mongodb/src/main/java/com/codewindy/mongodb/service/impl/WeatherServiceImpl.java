package com.codewindy.mongodb.service.impl;


import com.codewindy.mongodb.service.IWeatherService;

import javax.jws.WebService;

/**
 * @author jkwindy@126.com
 * @date 2019-12-15 20:49
 */
@WebService
public class WeatherServiceImpl  implements IWeatherService {
    @Override
    public String getWeatherInfo() {
        return "Sunny";
    }
}
