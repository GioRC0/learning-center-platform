package com.acme.learning.platform.learning.domain.model.aggregates;

import com.acme.learning.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learning.platform.learning.domain.model.valueobjects.EnrollmentStatus;
import com.acme.learning.platform.learning.domain.model.valueobjects.ProgressRecord;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
@Entity
public class Enrollment extends AbstractAggregateRoot<Enrollment> {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Embedded
    private AcmeStudentRecordId studentRecordId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Embedded
    private ProgressRecord progressRecord;

    protected EnrollmentStatus status;

    public Enrollment(){

    }

    public Enrollment(AcmeStudentRecordId studentRecordId, Course course){
        this.studentRecordId = studentRecordId;
        this.course = course;
        this.status = EnrollmentStatus.REQUESTED;
        this.progressRecord = new ProgressRecord();
    }

    public void confirm(){
        this.status = EnrollmentStatus.CONFIRMED;
        this.progressRecord.initializeProgressRecord(this,course.getLearningPath());
    }

    public void reject(){
        this.status = EnrollmentStatus.REJECTED;
    }

    public void cancel(){
        this.status = EnrollmentStatus.CANCELLED;
    }

    public long calculateDaysElapsed(){
        return this.progressRecord.calculateDaysElapsedForEnrollment(this);
    }

    public boolean isConfirmed(){
        return this.status == EnrollmentStatus.CONFIRMED;
    }

    public boolean isRejected(){
        return  this.status == EnrollmentStatus.REJECTED;
    }
}
