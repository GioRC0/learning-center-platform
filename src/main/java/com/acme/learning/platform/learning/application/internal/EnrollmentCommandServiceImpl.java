package com.acme.learning.platform.learning.application.internal;

import com.acme.learning.platform.learning.domain.model.aggregates.Course;
import com.acme.learning.platform.learning.domain.model.commands.EnrollStudentInCourseCommand;
import com.acme.learning.platform.learning.domain.services.EnrollmentCommandService;
import com.acme.learning.platform.learning.infrastructure.repositories.CourseRepository;
import com.acme.learning.platform.learning.infrastructure.repositories.EnrollmentRepository;
import com.acme.learning.platform.learning.infrastructure.repositories.StudentRepository;

public class EnrollmentCommandServiceImpl implements EnrollmentCommandService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentCommandServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Long handle(EnrollStudentInCourseCommand command) {
        /*studentRepository.findByStudentRecordId(command.studentRecordId().studentRecordId()).map(student ->
                Course course = courseRepository.find)

         */
        return 0L;
    }


}
