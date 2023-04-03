package ro.fasttrackit.curs13.student;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.fasttrackit.curs13.student.dto.Student;
import ro.fasttrackit.curs13.student.model.StudentEntity;
import ro.fasttrackit.curs13.student.service.StudentRepository;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentAppTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    StudentRepository studentRepository;


    @SneakyThrows
    @Test
    @DisplayName("GET /students")
    void getStudentsTest() {

        studentRepository.save(StudentEntity
                .builder()
                        .age(29)
                        .name("Raul")
                .build());
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/students"))
                .andDo(print())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();

        List<Student> result = new ObjectMapper().readValue(response, new TypeReference<List<Student>>() {
        });

        Assertions.assertThat(result)
                .extracting("name", "age")
                .containsExactly(Tuple.tuple("Raul", 29));//tuple asta e din cauza ca imi da o lista


    }
}
