
package com.qengine.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sergey
 */
@Entity
@Data
@NoArgsConstructor
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    private String  description;
    
    @Column(columnDefinition = "TEXT")
    private String  steps;
    
    private String expectedResult;
    
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private  Project project;
}
