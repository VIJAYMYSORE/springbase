package com.vmysore.springbase.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestPublisher {

    @Value("${amqp.queue.name}")
    private String queueName;
    private static Logger logger = LoggerFactory.getLogger(TestPublisher.class);

    private RabbitTemplate rabbitTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public TestPublisher(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void publish(Test test) throws Exception {
        String jsonString = objectMapper.writeValueAsString(test);
        rabbitTemplate.convertAndSend(queueName, jsonString);
    }
}
