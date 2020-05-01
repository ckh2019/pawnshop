package cn.ckh2019.pawnshop.service.goods.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chen Kaihong
 * 2020-02-21 15:36
 */
@Configuration
public class RabbitmqConfig {

    public static final String INFORM_EMAIL = "inform.email";

    public static final String INFORM_SMS = "inform.sms";

    public static final String INFORM_EMAIL_SMS = "inform.email.sms";


    public static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    public static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    public static final String EXCHANGE_TOPICS_INFORM="exchange_topics_inform";

    /**
     * 交换机配置
     * ExchangeBuilder提供了fanout、direct、topic、header交换机类型的配置
     * @return the exchange
     */
    @Bean(EXCHANGE_TOPICS_INFORM)
    public Exchange EXCHANGE_TOPICS_INFORM() {
        //durable(true)持久化，消息队列重启后交换机仍然存在
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFORM).build();
    }

    //声明队列
    @Bean(QUEUE_INFORM_SMS)
    public Queue QUEUE_INFORM_SMS() {
        Queue queue = new Queue(QUEUE_INFORM_SMS);
        return queue;
    }

    //声明队列
    @Bean(QUEUE_INFORM_EMAIL)
    public Queue QUEUE_INFORM_EMAIL() {
        Queue queue = new Queue(QUEUE_INFORM_EMAIL);
        return queue;
    }

    /**
     * channel.queueBind(INFORM_QUEUE_SMS,"inform_exchange_topic","inform.#.sms.#"); *
     * 绑定队列到交换机 .
     * @param queue the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    public Binding BINDING_QUEUE_INFORM_SMS(@Qualifier(QUEUE_INFORM_SMS) Queue queue, @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("inform.#.sms.#").noargs();
    }
    @Bean
    public Binding BINDING_QUEUE_INFORM_EMAIL(@Qualifier(QUEUE_INFORM_EMAIL) Queue queue, @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("inform.#.email.#").noargs();
    }

}

 class Rabbitmq1Config2 {

    public static final String MESSAGE_EMAIL = "message.email";

    public static final String MESSAGE_SMS = "message.sms";

    public static final String MESSAGE_EMAIL_SMS = "message.email.sms";


    public static final String QUEUE_EMAIL = "queue_email";
    public static final String QUEUE_SMS = "queue_sms";
    public static final String EXCHANGE_TOPICS_MESSAGE="exchange";

    /**
     * 交换机配置
     */
    @Bean(EXCHANGE_TOPICS_MESSAGE)
    public Exchange getExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_MESSAGE).build();
    }

    //声明队列
    @Bean(QUEUE_SMS)
    public Queue getQueueSms() {
        return new Queue(QUEUE_SMS);
    }

    //声明队列
    @Bean(QUEUE_EMAIL)
    public Queue getQueueEmail() {
        return new Queue(QUEUE_EMAIL);
    }

    /**
     * 绑定队列到交换机 .
     */
    @Bean
    public Binding BINDING_QUEUE_INFORM_SMS(@Qualifier(QUEUE_SMS) Queue q, @Qualifier(EXCHANGE_TOPICS_MESSAGE) Exchange e) {
        return BindingBuilder.bind(q).to(e).with("inform.#.sms.#").noargs();
    }
    @Bean
    public Binding BINDING_QUEUE_INFORM_EMAIL(@Qualifier(QUEUE_EMAIL) Queue q, @Qualifier(EXCHANGE_TOPICS_MESSAGE) Exchange e) {
        return BindingBuilder.bind(q).to(e).with("inform.#.email.#").noargs();
    }

}
