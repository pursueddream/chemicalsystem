package com.shengrong.chemicalsystem.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = MqConstant.QUEUE_A)
public class TestListener {
    // 发送 代码 rabbitTemplate.convertAndSend(MqConstant.EXCHANGE_DEF, null, "FFFSSS");
    @RabbitHandler
    public void process(String content) {
        log.info(content);
    }


}
