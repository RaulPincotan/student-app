package ro.fasttrackit.curs13.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.fasttrackit.curs13.student.model.StudentEntity;
import ro.fasttrackit.curs13.student.service.StudentRepository;

import java.util.List;

@SpringBootApplication
public class StudentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner dataLoader(StudentRepository repository) {
        return args -> {
            repository.saveAll(
                    List.of(
                            StudentEntity.builder()
                                    .name("Raul")
                                    .age(29)
                                    .build(),
                            StudentEntity.builder()
                                    .name("Pincotan")
                                    .age(40)
                                    .build(),
                            StudentEntity.builder()
                                    .name("Adrian")
                                    .age(32)
                                    .build()
                    )
            );
        };
    }
}
