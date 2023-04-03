package ro.fasttrackit.curs13.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseApiClientTest {
    private CourseApiClient client;

    @BeforeEach
    void setup() {
        this.client = new CourseApiClient("http://localhost:8001");
    }

    @Test
    void addStudentToCourse() {
        System.out.println(client.addStudentToCourse(1, 1));
    }
}
