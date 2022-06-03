package com.codewindy.h2.controller;

import com.codewindy.common.utils.ApiResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author codewindy
 * @date 2022-06-03 15:44
 * @since 1.0.0
 */
@RestController
public class SeigeController {

    @RequestMapping("/getInfo")
    public String getInfo(){
       String jsonStr ="{\"id\":\"002\",\"age\":\"24\",\"name\":\"Vadik\",\"email\":\"vadik@yahoo.co.in\"}";
        if (jsonStr == null) {

            int i = 1/0;

        }
        return jsonStr;
    }
}