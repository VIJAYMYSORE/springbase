package com.vmysore.springbase.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TestConsumer {

    private TestService testService;
    private ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(TestConsumer.class);

    @Autowired
    public TestConsumer(ObjectMapper objectMapper, TestService testService) {
        this.objectMapper = objectMapper;
        this.testService = testService;
    }

    public void receiveMessage(String testJson) {
        logger.info("Message Received");
        try {
            Test test = this.objectMapper.readValue(testJson, Test.class);
            Test savedTest = this.testService.create(test);
            System.out.println(savedTest);
            logger.info("Message Processed");
        } catch (IOException e) {
            logger.error("Failed Processing Message", e);
        }

    }
}
