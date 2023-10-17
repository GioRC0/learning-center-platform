package com.acme.learning.platform.learning.domain.model.aggregates;

import com.acme.learning.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learning.platform.learning.domain.model.valueobjects.ProfileId;
import com.acme.learning.platform.learning.domain.model.valueobjects.StudentPerformanceMetricSet;
import com.acme.learning.platform.profiles.domain.model.aggregates.Profile;
import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Embedded
    @Column(name = "acme_student_id")
    private AcmeStudentRecordId acmeStudentRecordId;

    @Embedded
    private ProfileId profileId;

    @Embedded
    private StudentPerformanceMetricSet studentPerformanceMetricSet;

    public Student(){
        this.acmeStudentRecordId = new AcmeStudentRecordId();
        this.studentPerformanceMetricSet = new StudentPerformanceMetricSet();
    }

    public Student(Long profileId){
        this();
        this.profileId = new ProfileId();
    }

    public void updateMetricsOnCourseCompleted(){
        this.studentPerformanceMetricSet = this.studentPerformanceMetricSet.incrementTotalCompletedCourses();
    }

    public void updateMetricsOnTutorialCompleted(){
        this.studentPerformanceMetricSet = this.studentPerformanceMetricSet.incrementTotalTutorials();
    }

    public String getStudentRecordId(){
        return this.acmeStudentRecordId.studentRecordId();
    }

    public Long getProfileId(){
        return this.profileId.profileId();
    }
}
