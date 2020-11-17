package com.codewindy.mongodb.controller;

import cn.hutool.core.util.IdUtil;
import com.codewindy.mongodb.pojo.ApiResult;
import com.codewindy.mongodb.service.MikrotikService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private RabbitTemplate rabbitTemplate;

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

    /**
     * 测试死信队列.
     *
     * @param p the p
     * @return the response entity
     */
    @RequestMapping("/dead")
    public ApiResult deadLetter(String p) {
        CorrelationData correlationData = new CorrelationData(IdUtil.fastSimpleUUID());
//        声明消息处理器  这个对消息进行处理  可以设置一些参数   对消息进行一些定制化处理   我们这里  来设置消息的编码  以及消息的过期时间  因为在.net 以及其他版本过期时间不一致   这里的时间毫秒值 为字符串
        MessagePostProcessor messagePostProcessor = message -> {
            MessageProperties messageProperties = message.getMessageProperties();
//            设置编码
            messageProperties.setContentEncoding("utf-8");
//            设置过期时间10*1000毫秒
            messageProperties.setExpiration("10000");
            return message;
        };
//         向DL_QUEUE 发送消息  10*1000毫秒后过期 形成死信
        rabbitTemplate.convertAndSend("DL_EXCHANGE", "DL_KEY", p, messagePostProcessor, correlationData);
        return null;
    }
}