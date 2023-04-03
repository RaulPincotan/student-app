package ro.fasttrackit.curs13.course.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.fastrackit.curs13.exceptions.ResourceNotFoundException;
import ro.fasttrackit.curs13.course.model.CourseEntity;
import ro.fasttrackit.curs13.course.model.CourseStudentEntity;
import ro.fasttrackit.curs13.student.client.StudentApiClient;
import ro.fasttrackit.curs13.student.dto.Student;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseStudentService {

    private final CourseRepository courseRepository;
    private final StudentApiClient studentApiClient;
    private final CourseStudentRepository courseStudentRepository;

    public CourseStudentEntity addStudentToCourse(int courseId, int studentId) {
        CourseEntity course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course with id " + courseId + " could not be found"));

        Student student = studentApiClient.getById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not found student with id " + studentId));

        CourseStudentEntity enrolledStudentToCourse = courseStudentRepository.save(CourseStudentEntity.builder()
                .course(course)
                .studentName(student.getName())
                .studentId(studentId)
                .build()
        );
        log.info("Student with id [{}] has been enrolled to course with id  [{}]", enrolledStudentToCourse.getStudentId(), courseId);
        return enrolledStudentToCourse;
    }

    public List<CourseStudentEntity> deleteCoursesForStudent(int studentId) {
        return courseStudentRepository.deleteAllByStudentId(studentId);
    }
}
