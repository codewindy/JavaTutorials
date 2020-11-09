package com.codewindy.mongodb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author codewindy
 * @date 2020-11-09 10:58 PM
 * @since 1.0.0
 */

/**
 * 发送消息确认：用来确认生产者 producer 将消息发送到 broker ，broker 上的交换机 exchange 再投递给队列 queue 的过程中，消息是否成功投递。
 *
 * 消息从 producer 到 rabbitmq broker 有一个 confirmCallback 确认模式。
 *
 * 消息从 exchange 到 queue 投递失败有一个 returnCallback 退回模式。
 *
 * 我们可以利用这两个 Callback 来确保消的 100% 送达。
 *
 * 1、 ConfirmCallback 确认模式
 * 消息只要被 rabbitmq broker 接收到就会触发 confirmCallback 回调 。
 *实现接口 ConfirmCallback ，重写其 confirm() 方法，方法内有三个参数 correlationData、ack、cause。
 *
 * correlationData：对象内部只有一个 id 属性，用来表示当前消息的唯一性。
 * ack：消息投递到 broker 的状态，true 表示成功。
 * cause：表示投递失败的原因。
 * 但消息被 broker 接收到只能表示已经到达 MQ 服务器，并不能保证消息一定会被投递到目标 queue 里。所以接下来需要用到 returnCallback 。
 *
 * ————————————————
 * 转自链接：https://learnku.com/articles/46580
 * docker run -d --name rabbitmq3.8.9 -p 5672:5672 -p 15672:15672  --hostname rabbitmq  -e RABBITMQ_DEFAULT_VHOST=/vhost -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin
 *
 * */
@Component
@Slf4j
public class ConfirmCallbackServiceImpl implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        if (!ack) {
            log.error("消息发送异常 producer -> rabbitmq broker");
        }else{
            log.info("发送者收到已经确认的回调，correlationData=={} , ack == {} , cause == {}", correlationData.getId() , ack , cause);
        }
    }
}