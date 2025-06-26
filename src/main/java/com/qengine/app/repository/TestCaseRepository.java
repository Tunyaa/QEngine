package com.qengine.app.repository;

import com.qengine.app.model.TestCase;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sergey
 */
public interface TestCaseRepository extends JpaRepository<TestCase, Long>{
    List<TestCase> findByProjectId(Long projectId);
}
