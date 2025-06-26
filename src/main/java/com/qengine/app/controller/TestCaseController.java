package com.qengine.app.controller;

import com.qengine.app.model.TestCase;
import com.qengine.app.repository.TestCaseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sergey
 */
@RestController
@RequestMapping("/api/test-cases")
public class TestCaseController {
    @Autowired
    private TestCaseRepository testCaseRepository;
    
    @PostMapping
    public TestCase createTestCase(@RequestBody TestCase testCase){
        return testCaseRepository.save(testCase);
    }
    
    @GetMapping("/by-project/{projectId}")
    public List<TestCase> getTestCasesByProject(@PathVariable Long projectId){
        return testCaseRepository.findByProjectId(projectId);
    }
}
