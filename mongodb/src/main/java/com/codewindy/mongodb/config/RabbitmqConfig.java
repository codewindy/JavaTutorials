package com.codewindy.mongodb.config;

import cn.hutool.core.map.MapUtil;
import com.codewindy.common.utils.RabbitmqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author codewindy
 * @date 2020-11-09 10:55 PM
 * @since 1.0.0
 */
@Configuration
@Slf4j
public class RabbitmqConfig {
    /* -------------------------------定义oder_queue order_exchange的绑定关系-----------------------------------------------*/

    @Bean(name = "orderQueue")
    public Queue orderQueue() {
        HashMap<String, Object> params = MapUtil.newHashMap(16);
        params.put(RabbitmqConstant.X_DEAD_LETTER_EXCHANGE, RabbitmqConstant.DLX_EXCHANGE_NAME);
        params.put(RabbitmqConstant.X_DEAD_LETTER_ROUTIN_KEY, RabbitmqConstant.DLX_ROUTING_ORDER_CANCEL);
        //params.put(RabbitmqConstant.X_EXPIRES,5000);
        params.put(RabbitmqConstant.X_MAX_LENGTH, 10);
        params.put(RabbitmqConstant.X_MESSAGE_TTL, 5000);
        //params.put(CommonConstant.X_MAX_PRIORITY,4);
        return QueueBuilder.durable(RabbitmqConstant.QUEUE_NAME).withArguments(params).build();
    }

    @Bean(name = "orderExchange")
    public TopicExchange orderExchange() {
        return ExchangeBuilder.topicExchange(RabbitmqConstant.EXCHANGE_NAME).durable(true).build();

    }

    @Bean
    public Binding orderTopicExchangeAndQueue(
            @Qualifier("orderExchange") TopicExchange orderExchange,
            @Qualifier("orderQueue") Queue orderQueue) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(RabbitmqConstant.ROUTING_KEY_NAME);
    }

    /* -------------------------------定义死信队列dlx_oder_queue dlx_order_exchange的绑定关系-----------------------------------------------*/

    @Bean(name = "dlxOrderQueue")
    public Queue dlxOrderQueue() {
        return QueueBuilder.durable(RabbitmqConstant.DLX_QUEUE_NAME).build();
    }

    @Bean(name = "dlxOrderExchange")
    public TopicExchange dlxOrderExchange() {
        return ExchangeBuilder.topicExchange(RabbitmqConstant.DLX_EXCHANGE_NAME).durable(true).build();
    }

    @Bean
    public Binding dlxOrderTopicExchangeAndQueue(
            @Qualifier("dlxOrderExchange") TopicExchange dlxOrderExchange,
            @Qualifier("dlxOrderQueue") Queue dlxOrderQueue) {
        return BindingBuilder.bind(dlxOrderQueue).to(dlxOrderExchange).with(RabbitmqConstant.DLX_ROUTING_KEY_NAME);
    }


}
