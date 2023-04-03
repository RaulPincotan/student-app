package ro.fasttrackit.curs13.student.controller;

import org.springframework.stereotype.Component;
import ro.fastrackit.curs13.utils.ModelMapper;
import ro.fasttrackit.curs13.student.dto.Student;
import ro.fasttrackit.curs13.student.model.StudentEntity;

@Component
public class StudentMapper implements ModelMapper<Student, StudentEntity> {

    public Student toApi(StudentEntity entity) {
        return Student.builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .build();
    }

    public StudentEntity toEntity(Student dto) {
        return StudentEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .age(dto.getAge())
                .build();
    }
}


