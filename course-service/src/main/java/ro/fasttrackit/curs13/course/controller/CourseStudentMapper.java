package ro.fasttrackit.curs13.course.controller;

import org.springframework.stereotype.Component;
import ro.fastrackit.curs13.utils.ModelMapper;
import ro.fasttrackit.curs13.course.model.CourseEntity;
import ro.fasttrackit.curs13.course.model.CourseStudentEntity;
import ro.fasttrackit.curs13.dto.CourseStudent;

import static java.util.Optional.ofNullable;

@Component
public class CourseStudentMapper implements ModelMapper<CourseStudent, CourseStudentEntity> {
    @Override
    public CourseStudent toApi(CourseStudentEntity entity) {
        return CourseStudent.builder()
                .id(entity.getId())
                .courseId(ofNullable(entity.getCourse())
                        .map(CourseEntity::getId)
                        .orElse(null))
                .studentId(entity.getStudentId())
                .studentName(entity.getStudentName())
                .build();
    }

    @Override
    public CourseStudentEntity toEntity(CourseStudent dto) {
        return CourseStudentEntity.builder()
                .course(CourseEntity.builder()
                        .id(dto.getId())
                        .build())
                .studentId(dto.getStudentId())
                .studentName(dto.getStudentName())
                .build();
    }
}
