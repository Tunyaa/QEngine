package com.qengine.app.controller;

import com.qengine.app.model.Project;
import com.qengine.app.model.User;
import com.qengine.app.repository.ProjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sergey
 */
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project, @AuthenticationPrincipal User user){
        project.setOwner(user);
        return ResponseEntity.ok(projectRepository.save(project));
    }
    
    @GetMapping
    public List<Project> getUserProjects(@AuthenticationPrincipal User user){
        return projectRepository.findByOwner(user);
    }
}
