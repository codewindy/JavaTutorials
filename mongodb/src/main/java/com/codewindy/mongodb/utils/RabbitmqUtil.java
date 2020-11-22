package com.codewindy.mongodb.utils;

import cn.hutool.core.util.IdUtil;
import com.codewindy.mongodb.service.impl.ConfirmCallbackServiceImpl;
import com.codewindy.mongodb.service.impl.ReturnCallbackServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.connection.PublisherCallbackChannel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;

/**
 * @author codewindy
 * @date 2020-11-09 11:28 PM
 * @since 1.0.0
 */
@Slf4j
@Component
public class RabbitmqUtil implements InitializingBean {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String exchange, String routingKey, Object msg) {

        /**
         * 确保消息发送失败后可以重新返回到队列中
         * 注意：yml需要配置 publisher-returns: true
         */
        rabbitTemplate.setMandatory(true);
        //rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        /**
         * 消费者确认收到消息后，手动ack回执回调处理
         */
        //rabbitTemplate.setConfirmCallback(confirmCallbackService);

        /**
         * 消息投递到队列失败回调处理
         */
       // rabbitTemplate.setReturnCallback(returnCallbackService);
        /**
         * 发送消息
         */
        rabbitTemplate.convertAndSend(exchange, routingKey, msg,
                message -> {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    //message.getMessageProperties().setExpiration("5000");
                    message.getMessageProperties().setContentEncoding(StandardCharsets.UTF_8.name());
                    //message.getMessageProperties().setPriority(4);
                    return message;
                },
                new CorrelationData(IdUtil.fastSimpleUUID()));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        rabbitTemplate.setConfirmCallback(new ConfirmCallbackServiceImpl());
        rabbitTemplate.setReturnCallback(new ReturnCallbackServiceImpl());
    }
}