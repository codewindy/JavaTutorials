package com.codewindy.common.utils;

public interface RabbitmqConstant {
    String  X_MESSAGE_TTL = "x-message-ttl";
    String  X_EXPIRES = "x-expires";
    String  X_MAX_LENGTH = "x-max-length";
    String  X_MAX_PRIORITY = "x-max-priority";
    /**
     * 死信队列 交换机标识符
     */
    String  X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    /**
     * 死信队列交换机绑定键标识符
     */
    String  X_DEAD_LETTER_ROUTIN_KEY = "x-dead-letter-routing-key";

    String DLX_EXCHANGE_NAME ="dlx_order_exchange";
    String DLX_QUEUE_NAME ="dlx_order_queue";
    String DLX_ROUTING_KEY_NAME ="dlx.order.#";

    String DLX_ROUTING_ORDER_CANCEL ="dlx.order.cancel";


    String EXCHANGE_NAME ="order_exchange";
    String QUEUE_NAME ="order_queue";
    String ROUTING_KEY_NAME ="order.#";
    String ROUTING_ORDER_CANCEL ="order.cancel";
}
