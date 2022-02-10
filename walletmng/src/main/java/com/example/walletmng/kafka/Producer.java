package com.example.walletmng.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    public static final String topic="NewTopic";

    @Autowired
    private KafkaTemplate<String,String>kafkaTemplate;

    public void publishToTopic(String message)
    {
        System.out.println("Publishing to topic"+topic);
        this.kafkaTemplate.send(topic,message);
    }
}
