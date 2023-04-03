package ro.fasttrackit.curs13.student.dto;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentSerializationTest {

    @Test
    void studentToJson() throws JsonProcessingException {
        Student student = new Student(1, "Raul", 29);
        String result = new ObjectMapper().writeValueAsString(student);

        assertEquals(result, """
              {"id":1,"name":"Raul","age":29}""");
    }

    @SneakyThrows
    @Test
    void jsonToStudent() {
        String json = """
                {"id":1,"name":"Raul","age":29}""";
        Student student = new ObjectMapper().readValue(json, Student.class);
        assertEquals(student.getId(),1);
        assertEquals(student.getName(),"Raul");
        assertEquals(student.getAge(),29);
    }
}
