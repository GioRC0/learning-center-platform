package com.acme.learning.platform.learning.domain.model.valueobjects;

import com.acme.learning.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.learning.platform.learning.domain.model.entities.ProgressRecordItem;
import com.acme.learning.platform.learning.domain.model.entities.Tutorial;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ProgressRecord {
    @OneToMany(mappedBy = "enrollment")
    private List<ProgressRecordItem> progressRecordItems;

    public ProgressRecord(){
        progressRecordItems = new ArrayList<>();
    }

    public void initializeProgressRecord(Enrollment enrollment, LearningPath learningPath){
        Tutorial tutorial = learningPath.getFirstTutorialLearningPath();
        ProgressRecordItem progressRecordItem = new ProgressRecordItem(enrollment,tutorial);
        progressRecordItems.add(progressRecordItem);
    }

    private ProgressRecordItem getProgressRecordItemWithTutorialId(Long tutorialId){
        return progressRecordItems.stream().filter(progressRecordItem -> progressRecordItem.getTutorial().getId().equals(tutorialId)).findFirst().orElse(null);
    }

    private boolean hasAnItemProgress(){
        return progressRecordItems.stream().anyMatch(ProgressRecordItem::isInProggress);
    }

    public Long calculateDaysElapsedForEnrollment(Enrollment enrollment){
        return progressRecordItems.stream().filter(progressRecordItem -> progressRecordItem.getEnrollment().equals(enrollment)).mapToLong(ProgressRecordItem::calculateDaysElapsed).sum();
    }

    public void startTutorial(Long tutorialId){
        if (hasAnItemProgress()) throw new IllegalStateException("A tutorial is already in progress");

        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (progressRecordItem != null){
            if (progressRecordItem.isNotStarted()) progressRecordItem.start();
            else throw new IllegalStateException("Tutorial with given Id is already started or completed");
        } else throw new IllegalArgumentException("Tutorial with given id is not found in progress record");
    }

    public void completeTutorial(Long tutorialId, LearningPath learningPath){
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (progressRecordItem != null) progressRecordItem.complete();
    }
}
