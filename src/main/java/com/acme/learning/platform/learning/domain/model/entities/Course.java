package com.acme.learning.platform.learning.domain.model.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Course {
    @Id
    private Long id;
    private String title;
    private String description;

    @Embedded
    @Getter
    private LearningPath learningPath;
}
