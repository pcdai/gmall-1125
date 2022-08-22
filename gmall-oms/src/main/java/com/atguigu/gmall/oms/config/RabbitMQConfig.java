package com.atguigu.gmall.oms.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @author: dpc
 * @data: 2020/6/2,9:39
 */
@Configuration
@Slf4j
public class RabbitMQConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息到达交换机");
        } else {
            log.error("消息发送到交换机失败:{}", cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("消息没有到达队列,交换机:{},路由键:{},消息内容:{}", exchange, routingKey, message.toString());
    }

    //1 业务交换及机 使用已有的ORDER_EXCHANGE
    //2 延迟队列 ，延迟时间 死信交换机 死信 rk 不能有消费者 超时就会到交换机
    @Bean
    public Queue ttlQueue(){
        HashMap<String, Object> map = new HashMap<>();
        //设置延迟时间
        map.put("x-message-ttl",90000);
        //设置死信交换机
        map.put("x-dead-letter-exchange","ORDER_EXCHANGE");
        map.put("x-dead-letter-routing-key","order.dead");
        return new Queue("ORDER_TTL_QUEUE",true,false,false,map);
    }
    //3 延迟队列绑定到业务交换机
    @Bean
    public Binding ttlBind(){
        return new Binding("ORDER_TTL_QUEUE", Binding.DestinationType.QUEUE,"ORDER_EXCHANGE","order.ttl",null);
    }
    //4 死信交换机
    //5 死信队列 本质就是普通队列，放的是死信消息
    @Bean
    public Queue deadQueue(){
        return new Queue("ORDER_DEAD_QUEUE",true,false,false);
    }
    //6 死信队列绑定到死信交换机
    @Bean
    public Binding deadBind(){
        return new Binding("ORDER_DEAD_QUEUE", Binding.DestinationType.QUEUE,"ORDER_EXCHANGE","order.dead",null);
    }
}
