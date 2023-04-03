package ro.fasttrackit.curs13.course.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs13.course.model.CourseEntity;
import ro.fasttrackit.curs13.student.client.StudentApiClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;


    public List<CourseEntity> getAll() {
        return courseRepository.findAll();
    }

}
