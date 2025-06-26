
package com.qengine.app.repository;

import com.qengine.app.model.Project;
import com.qengine.app.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sergey
 */
public interface ProjectRepository extends JpaRepository<Project, Long>{
    List<Project> findByOwner(User user);
}
