package com.rocket.cn;


import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "sendTranscationMessage",topic = "sendTranscationMessage")
public class ResciveTransaction implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("李四+钱成功");
        System.out.println(s);
    }
}
