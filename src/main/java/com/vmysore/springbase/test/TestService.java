package com.vmysore.springbase.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {
    private TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> getTests() {
        List<Test> tests = new ArrayList<>();
        this.testRepository.findAll().forEach(tests::add);
        return tests;
    }

    public Test create(Test test) {
        return this.testRepository.save(test);
    }
}
