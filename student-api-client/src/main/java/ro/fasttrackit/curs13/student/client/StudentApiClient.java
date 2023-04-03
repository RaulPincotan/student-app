package ro.fasttrackit.curs13.student.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ro.fasttrackit.curs13.student.dto.Student;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Component
public class StudentApiClient {

    private final String baseUrl;
    private final RestTemplate restTemplate;

    public StudentApiClient(@Value("${student.service.location:NOT_DEFINED}") String baseUrl) {
        this.baseUrl = baseUrl;
        restTemplate = new RestTemplate();
    }

    public List<Student> getStudents() {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/students")
                .toUriString();

//        log.info("Get all students " + url);

        return restTemplate.exchange(url, HttpMethod.GET,
                        new HttpEntity<>(null),
                        new ParameterizedTypeReference<List<Student>>() {
                        })
                .getBody();
    }

    public Student deleteStudent(int id) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/students/")
                .path(String.valueOf(id))
                .toUriString();

        return restTemplate.exchange(url, HttpMethod.DELETE,
                        new HttpEntity<>(null),
                        Student.class)
                .getBody();
    }

    public Optional<Student> getById(int studentId) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/students/")
                .path(String.valueOf(studentId))
                .toUriString();

        try {
            return ofNullable(restTemplate.getForObject(url,
                    Student.class));
        } catch (HttpClientErrorException exception) {
            return Optional.empty();
        }
    }
}
