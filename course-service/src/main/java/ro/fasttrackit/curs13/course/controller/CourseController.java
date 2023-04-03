package ro.fasttrackit.curs13.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs13.course.service.CourseService;
import ro.fasttrackit.curs13.course.service.CourseStudentService;
import ro.fasttrackit.curs13.dto.Course;
import ro.fasttrackit.curs13.dto.CourseStudent;

import java.util.List;

@RestController
@RequestMapping("courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseMapper courseMapper;
    private final CourseService courseService;
    private final CourseStudentMapper courseStudentMappers;
    private final CourseStudentService courseStudentService;


    @GetMapping
   public List<Course> getCourses() {
        return courseMapper.toApi(courseService.getAll());
    }

    @PostMapping("{courseId}/students/{studentId}")
    CourseStudent addStudentToCourse(@PathVariable Integer courseId, @PathVariable Integer studentId) {
        return courseStudentMappers.toApi(courseStudentService.addStudentToCourse(courseId, studentId));
    }
}
