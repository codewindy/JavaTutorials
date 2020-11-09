package com.codewindy.mongodb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author codewindy
 * @date 2020-11-09 11:06 PM
 * @since 1.0.0
 */

/**
 * 如果消息未能投递到目标 queue 里将触发回调 returnCallback ，一旦向 queue 投递消息未成功，这里一般会记录下当前消息的详细投递数据，方便后续做重发或者补偿等操作。
 */
@Slf4j
@Component
public class ReturnCallbackServiceImpl implements RabbitTemplate.ReturnCallback {


    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

        log.info("returnMessage====> message=={}, replyCode={}, replyText=={}, exchange=={}, routingKey=={}", message, replyCode, replyText, exchange , routingKey);
    }
}