package com.acme.learning.platform.learning.infrastructure.repositories;

import com.acme.learning.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.learning.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    List<Enrollment> findAllByAcmeStudentRecordId(AcmeStudentRecordId studentRecordId);
}
