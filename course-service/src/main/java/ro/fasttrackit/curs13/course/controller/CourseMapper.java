package ro.fasttrackit.curs13.course.controller;

import org.springframework.stereotype.Component;
import ro.fastrackit.curs13.utils.ModelMapper;
import ro.fasttrackit.curs13.course.model.CourseEntity;
import ro.fasttrackit.curs13.dto.Course;

@Component
public class CourseMapper implements ModelMapper<Course, CourseEntity> {
    @Override
    public Course toApi(CourseEntity entity) {
        return Course.builder()
                .id(entity.getId())
                .discipline(entity.getDiscipline())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public CourseEntity toEntity(Course dto) {
        return CourseEntity.builder()
                .id(dto.getId())
                .discipline(dto.getDiscipline())
                .description(dto.getDescription())
                .build();
    }
}
