package com.acme.learning.platform.learning.domain.model.entities;

import com.acme.learning.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.learning.platform.learning.domain.model.valueobjects.ProgressStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class ProgressRecordItem {
    @Id
    private Long id;
    @Getter
    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;
    @Getter
    @ManyToOne
    @JoinColumn(name = "tutorial_id")
    private Tutorial tutorial;
    private ProgressStatus status;
    private Date startedAt;
    private Date completedAt;

    public ProgressRecordItem(Enrollment enrollment,Tutorial tutorial){
        this.enrollment = enrollment;
        this.tutorial = tutorial;
        this.status = ProgressStatus.NOT_STARTED;
    }
    public ProgressRecordItem(){

    }

    public void start(){
        this.status = ProgressStatus.STARTED;
        this.startedAt = new Date();
    }

    public void complete(){
        this.status = ProgressStatus.COMPLETED;
        this.completedAt = new Date();
    }

    public boolean isCompleted(){
        return this.status == ProgressStatus.COMPLETED;
    }

    public boolean isInProggress(){
        return this.status == ProgressStatus.STARTED;
    }

    public boolean isNotStarted(){
        return this.status == ProgressStatus.NOT_STARTED;
    }

    public long calculateDaysElapsed(){
        if(isNotStarted()) return 0;

        var defaultTimeZone = java.time.ZoneId.systemDefault();

        var fromDate = this.startedAt.toInstant();

        var toDate = this.completedAt == null ?
                LocalDate.now().atStartOfDay(defaultTimeZone).toInstant():
                this.completedAt.toInstant();
        return java.time.Duration.between(fromDate,toDate).toDays();
    }
}
