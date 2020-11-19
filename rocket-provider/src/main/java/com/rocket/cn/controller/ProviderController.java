package com.rocket.cn.controller;

import com.rocket.cn.entity.Huige;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;



   //同步发送
    @GetMapping("sendSync")
    public String sendSync(){
        SendResult sendResult = rocketMQTemplate.syncSend("sendSync", "发送同步消息");
        System.out.println(sendResult);
        return "OK";
    }

    @GetMapping("sendSyncObject")
    public String sendSyncObject(){
        Huige huige = new Huige();
        huige.setId(1);
         huige.setJob("打工人");
         huige.setDesc("社畜");
        SendResult sendResult = rocketMQTemplate.syncSend("sendSyncObject", huige);
        System.out.println(sendResult);
        return "OK";
    }
    //异步发送
    @GetMapping("sendAsync")
    public String sendAsync(){
        Huige huige = new Huige();
        huige.setId(1);

       rocketMQTemplate.asyncSend("sendAsync", "发送异步消息", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("消息发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("代码异常");
            }
        });

        System.out.println("执行完成");
        return "OK";
    }
    //单项发送发送
    @GetMapping("sendWayMessage")
    public String sendWayMessage(){
        rocketMQTemplate.sendOneWay("sendWayMessage","这是单项消息");
        return "OK";
    }
    //顺序发送发送
    @GetMapping("sendOrderMessage")
    public String sendOrderMessage(){
        rocketMQTemplate.syncSendOrderly("sendOrderMessage","下订单","aa");
        rocketMQTemplate.syncSendOrderly("sendOrderMessage","订单付款","aa");
        rocketMQTemplate.syncSendOrderly("sendOrderMessage","订单完成","aa");
        rocketMQTemplate.syncSendOrderly("sendOrderMessage","订单退货","aa");
        return "OK";
    }

    //延时发送
    @GetMapping("sendDelayMessage")
    public String sendDelayMessage(){
        SendResult sendResult = rocketMQTemplate.syncSend("sendDelayMessage",
                MessageBuilder.withPayload("延时消息").build(), 2000, 3);
        return "OK";
    }


    //消息过滤
    @GetMapping("sendTagSync")
    public String sendTagSync(){
        SendResult sendResult1 = rocketMQTemplate.syncSend("sendTagSync:Tga1", "我是Tga1");
        SendResult sendResult2= rocketMQTemplate.syncSend("sendTagSync:Tga2", "我是Tga2");
        SendResult sendResult3 = rocketMQTemplate.syncSend("sendTagSync:Tga3", "我是Tga3");
        SendResult sendResult4 = rocketMQTemplate.syncSend("sendTagSync:Tga4", "我是Tga4");

        return "OK";
    }


    //事务消息
    @GetMapping("sendTranscationMessage")
    public String sendTranscationMessage(){

        Message<String> message = MessageBuilder.withPayload("我是事务消息").build();
        System.out.println("张三开始给李四转账了");
        TransactionSendResult sendTranscationMessage = rocketMQTemplate.sendMessageInTransaction(
                "sendTranscationMessage", message, null);

        return "OK";
    }



}
