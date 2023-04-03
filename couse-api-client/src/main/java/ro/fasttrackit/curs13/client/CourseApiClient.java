package ro.fasttrackit.curs13.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ro.fasttrackit.curs13.dto.CourseStudent;

import java.util.Optional;

import static java.lang.String.valueOf;
import static java.util.Optional.ofNullable;


@Component
public class CourseApiClient {

    private final String baseUrl;
    private final RestTemplate restTemplate;

    public CourseApiClient(@Value("${course.service.location:NOT_DEFINED}") String baseUrl) {
        this.baseUrl = baseUrl;
        restTemplate = new RestTemplate();
    }

    public Optional<CourseStudent> addStudentToCourse(int studentId, int courseId) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/courses/")
                .path(valueOf(courseId))
                .path("/students/")
                .path(String.valueOf(studentId))
                .toUriString();
        try {
            return Optional.ofNullable(restTemplate.postForObject(url, null, CourseStudent.class));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
