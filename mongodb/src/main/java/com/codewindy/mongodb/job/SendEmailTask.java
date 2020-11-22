package com.codewindy.mongodb.job;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.codewindy.mongodb.pojo.User;
import com.codewindy.mongodb.service.UserService;
import com.codewindy.common.utils.SpringBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jkwindy@126.com
 * @date 2019-03-27 21:18
 */
@Component
@Slf4j
public class SendEmailTask implements Runnable{

    @Autowired
    private UserService userService;

    @Override
    @Scheduled(cron = "*/5 * * * * ?")
    public void run() {
        log.info("now()==============={}", DateUtil.now());
        List<User> list = userService.getList();
        System.out.println("list = " + JSONObject.toJSONString(list));
        JavaMailSender bean = SpringBeanUtils.getBean(JavaMailSender.class);
        System.out.println("bean = " + bean);
    }
}
