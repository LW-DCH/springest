package com.rocket.cn.controller;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * @author:刘伟
 * @date:2020/11/19 19:18
 * @description:
 */
@RocketMQTransactionListener
public class MyLocalListener implements RocketMQLocalTransactionListener {

    //提交事务的状态
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        System.out.println("张三转账成功");
        return RocketMQLocalTransactionState.COMMIT;
    }
    //回查机制
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        System.out.println("checkLocalTransaction执行了");
        return RocketMQLocalTransactionState.COMMIT;
    }
}
