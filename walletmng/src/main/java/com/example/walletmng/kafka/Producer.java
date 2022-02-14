package com.example.walletmng.kafka;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger= LogManager.getLogger(Producer.class);

    public static final String topic="NewTopic";

    @Autowired
    private KafkaTemplate<String,String>kafkaTemplate;

    public void publishToTopic(String message)
    {
        logger.debug("Publishing to topic"+topic);
        this.kafkaTemplate.send(topic,message);
    }
}
