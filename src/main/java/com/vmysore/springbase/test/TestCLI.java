package com.vmysore.springbase.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
public class TestCLI implements CommandLineRunner {
    private RestTemplate restTemplate;
    public static String BaseURL = "http://localhost:8000/api/tests";
    @Autowired
    public TestCLI(RestTemplate restTemplate) {
        super();
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Test[] testArray = this.restTemplate.getForObject(BaseURL, Test[].class);
        List<Test> tests = Arrays.asList(testArray);
        tests.forEach(System.out::println);
    }
}
