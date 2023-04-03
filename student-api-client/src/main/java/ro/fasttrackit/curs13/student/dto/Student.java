package ro.fasttrackit.curs13.student.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Student.StudentBuilder.class)
public class Student {
    int id;
    String name;
    int age;

    @JsonPOJOBuilder(withPrefix = "")
    public static class StudentBuilder {
    }
}

/*
din cauza problemei cu jackson din cauza ca am pus value care il face imutabiul si atunci punem @JsonDeseruaialize...
 */
