package com.codewindy.mongodb.controller;

import com.codewindy.mongodb.pojo.ApiResult;
import com.codewindy.mongodb.service.MikrotikService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author codewindy
 * @date 2020-03-25 9:36 PM
 * @since 1.0.0
 */
@RestController
@Api(value = "pppoe服务器")
@Slf4j
@RequestMapping("/mikrotik")
public class MikroTikController {

    @Autowired
    private MikrotikService mikrotikService;

    @GetMapping("/login")
    @ApiOperation(value = "登录pppoe服务器", notes = "登录pppoe服务器")
    public ApiResult login(@ApiParam(name = "username", value = "用户名", required = true) @RequestParam("username") String username,
                           @ApiParam(name = "password", value = "密码", required = true) @RequestParam("password") String password) {

        return mikrotikService.login(username, password);
    }

    @PostMapping("/createPPPOEServer")
    @ApiOperation(value = "创建pppoe服务器", notes = "创建pppoe服务器")
    public ApiResult createPPPOEServer(@RequestParam("ipPool") String ipPool) {
        return mikrotikService.createPPPOEServer(ipPool);
    }

    @GetMapping("/getPcapFileDetail")
    @ApiOperation(value = "解析pcap数据包恢复账号密码", notes = "解析pcap数据包恢复账号密码")
    public ApiResult getPcapFileDetail() {
        return mikrotikService.getPcapFileDetail();
    }

    @PostMapping("/downloadPPPOESession")
    @ApiOperation(value = "下载抓包数据文件pppoeSession", notes = "下载抓包数据文件pppoeSession")
    public ApiResult downloadPPPOESession() {
        return mikrotikService.downloadPPPOESession();
    }

    @PostMapping("/parseLocalPcapFile")
    @ApiOperation(value = "解析下载到本地的pcap数据包", notes = "解析下载到本地的pcap数据包")
    public ApiResult parseLocalPcapFile() {
        return mikrotikService.parseLocalPcapFile();
    }

}