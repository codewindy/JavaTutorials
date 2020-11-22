package com.codewindy.mysql;

import com.codewindy.common.utils.RabbitmqConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author codewindy
 * @date 2020-11-09 11:42 PM
 * @since 1.0.0
 */
@Slf4j
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = RabbitmqConstant.DLX_QUEUE_NAME, durable = "true"),
        exchange = @Exchange(name = RabbitmqConstant.DLX_EXCHANGE_NAME,
                type = ExchangeTypes.TOPIC,
                ignoreDeclarationExceptions = "true"),
        key = {RabbitmqConstant.DLX_ROUTING_ORDER_CANCEL}
))
public class ConsumerMessageHandler {

    @RabbitHandler
    public void messageHandler(String msg, Channel channel, Message message) throws IOException {

        try {
            log.info("消费者收到消息：{}", msg);

            //TODO 具体业务
            //int i = 1/0;

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        }  catch (Exception e) {

            if (message.getMessageProperties().getRedelivered()) {

                log.error("消息已重复处理失败,拒绝再次接收...");
                // 拒绝消息
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {

                log.error("消息即将再次返回队列处理...");

                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }
}