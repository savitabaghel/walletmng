package com.example.walletmng.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics="NewTopic",groupId = "mygroup")
    public void consumeFromTopic(String message)
    {
        System.out.println("Consumed message"+message);
    }

}
