package com.rocket.cn;


import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "sendOrderMessage",topic = "sendOrderMessage",consumeMode =ConsumeMode.ORDERLY )
public class ResciveOrderMessage implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println(s);
    }
}
