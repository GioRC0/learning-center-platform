package com.acme.learning.platform.learning.domain.model.entities;

import com.acme.learning.platform.learning.domain.model.aggregates.Course;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
public class LearningPathItem {
    @Id
    @Getter
    private Long id;
    @ManyToOne
    private Course course;
    @ManyToOne
    @Getter
    private Tutorial tutorial;

    @Getter
    private  Long nextItemId;

    public LearningPathItem(Tutorial tutorial, Long nextItemId){
        this.tutorial = tutorial;
        this.nextItemId = nextItemId;
    }

    public LearningPathItem(){
        this.tutorial = null;
        this.nextItemId = null;
    }

    public void updateNextItemID(Long nextItemId){
        this.nextItemId = nextItemId;
    }
}
