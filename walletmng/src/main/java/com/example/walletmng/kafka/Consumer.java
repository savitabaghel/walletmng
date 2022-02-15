package com.example.walletmng.kafka;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private static final Logger logger= LogManager.getLogger(Consumer.class);

    @KafkaListener(topics="NewTopic",groupId = "mygroup")
    public void consumeFromTopic(String message)
    {
        logger.info("Consumed message"+message);
    }

}
