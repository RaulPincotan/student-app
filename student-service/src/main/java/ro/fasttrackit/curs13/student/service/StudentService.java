package ro.fasttrackit.curs13.student.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs13.student.model.StudentEntity;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentNotificationService studentNotificationService;

    public List<StudentEntity> getStudents() {
        return studentRepository.findAll();
    }

    public Optional<StudentEntity> deleteStudent(Integer id) {
        Optional<StudentEntity> studentOptional = studentRepository.findById(id);
        studentOptional.ifPresent(this::deleteExistingStudent);
        return studentOptional;
    }

    private void deleteExistingStudent(StudentEntity studentEntity) {
        studentRepository.delete(studentEntity);
        log.info("deleting [{}]", studentEntity);
        studentNotificationService.notifyStudentDeleted(studentEntity);
    }

    public Optional<StudentEntity> getStudent(Integer studentId) {
        return studentRepository.findById(studentId);
    }
}
