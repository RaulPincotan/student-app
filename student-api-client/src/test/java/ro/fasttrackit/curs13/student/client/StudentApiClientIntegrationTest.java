package ro.fasttrackit.curs13.student.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class StudentApiClientIntegrationTest {
    @Autowired
    private StudentApiClient studentClient;

    @BeforeEach
    void setup() {
        this.studentClient = new StudentApiClient("http://localhost:8000");
    }

    @Test
    void getStudents() {
        System.out.println(studentClient.getStudents());
    }

    @Test
    void deleteStudent() {
        System.out.println(studentClient.deleteStudent(1));
    }
}
