package ro.fasttrackit.curs13.student.model;

import lombok.Value;

@Value
public class StudentFilter {

    String firstName;
    String lastName;
    String address;
    Integer age;
    Integer year;
}
