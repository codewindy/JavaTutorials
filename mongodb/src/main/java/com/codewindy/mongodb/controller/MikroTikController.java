package com.codewindy.mongodb.controller;

import com.codewindy.mongodb.pojo.ApiResponseJson;
import com.codewindy.mongodb.service.MikrotikService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import me.legrange.mikrotik.ApiConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author codewindy
 * @date 2020-03-25 9:36 PM
 * @since 1.0.0
 */
@RestController
@Api(value = "pppoe服务器", tags = "pppoe服务器")
@Slf4j
@RequestMapping("/mikrotik")
public class MikroTikController {

    @Autowired
    private MikrotikService mikrotikService;

    @GetMapping("/login")
    public ApiResponseJson login(@RequestParam("username")String username,@RequestParam("password")String password){

        return mikrotikService.login(username,password);
    }

    @PostMapping("/createPPPOEServer")
    public ApiResponseJson createPPPOEServer(@RequestParam("ipPoolRange")String ipPoolRange){
        return mikrotikService.createPPPOEServer(ipPoolRange);
    }

    @GetMapping("/getPcapFileDetail")
    public ApiResponseJson getPcapFileDetail(){
        return mikrotikService.getPcapFileDetail();
    }
}