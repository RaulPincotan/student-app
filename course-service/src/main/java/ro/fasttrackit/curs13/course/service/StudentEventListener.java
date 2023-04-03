package ro.fasttrackit.curs13.course.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs13.course.model.CourseStudentEntity;
import ro.fasttrackit.curs13.student.events.StudentEvent;

import java.util.List;

import static ro.fasttrackit.curs13.student.events.StudentEventType.ADDED;
import static ro.fasttrackit.curs13.student.events.StudentEventType.DELETED;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentEventListener {

    private final CourseStudentService courseStudentService;

    @RabbitListener(queues = "#{studentsQueue.name}")
    void processStudentEvent(StudentEvent event) {
        if (event.getType() == DELETED) {
            List<CourseStudentEntity> deletedCourses = courseStudentService.deleteCoursesForStudent(event.getStudent().getId());
            log.info("Courses for student id [{}] were deleted. Courses deleted: [{}] ", event.getStudent().getName(), deletedCourses);
        }
    }
}
