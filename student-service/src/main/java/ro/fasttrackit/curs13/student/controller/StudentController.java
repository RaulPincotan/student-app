package ro.fasttrackit.curs13.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fastrackit.curs13.exceptions.ResourceNotFoundException;
import ro.fasttrackit.curs13.student.dto.Student;
import ro.fasttrackit.curs13.student.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentMapper studentMapper;
    private final StudentService studentService;

    @GetMapping
    List<Student> getStudents() {
        return studentMapper.toApi(studentService.getStudents());
    }

    @GetMapping("{studentId}")
    Student getStudent(@PathVariable Integer studentId) {
        return studentService.getStudent(studentId)
                .map(studentMapper::toApi)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id "+studentId+ " could not be found!"));
    }

    @DeleteMapping("{id}")
    Student deleteStudent(@PathVariable Integer id) {
        return studentService.deleteStudent(id)
                .map(studentMapper::toApi)
                .orElse(null);
    }
}
