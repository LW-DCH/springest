package com.rocket.cn;


import com.rocket.cn.entity.Huige;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "ccc",topic = "sendSyncObject")
public class ResciveObject implements RocketMQListener<Huige> {

    @Override
    public void onMessage(Huige huige) {
        System.out.println(huige);
    }
}
