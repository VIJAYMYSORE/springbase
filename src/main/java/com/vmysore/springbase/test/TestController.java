package com.vmysore.springbase.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api")
public class TestController {

    private TestService testService;
    private TestPublisher testPublisher;

    @Autowired
    public TestController(TestService testService, TestPublisher testPublisher) {
        this.testService = testService;
        this.testPublisher = testPublisher;
    }

    @GetMapping("/tests")
    public List<Test> getAll() {
        return this.testService.getTests();
    }

    @PostMapping("/tests")
    public String createTest(@RequestBody Test test) throws Exception {
        this.testPublisher.publish(test);
        return "OK";
    }
}
