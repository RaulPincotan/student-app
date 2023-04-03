package ro.fasttrackit.curs13.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.fasttrackit.curs13.course.model.CourseEntity;
import ro.fasttrackit.curs13.course.service.CourseRepository;

import java.util.List;

@SpringBootApplication(scanBasePackages = {
        "ro.fasttrackit.curs13.course",
        "ro.fasttrackit.curs13.exceptions",
        "ro.fasttrackit.curs13.student.client"
//       "ro.fasttrackit.course13.commons.client",
//       "ro.fasttrackit.course13.exceptions"

})
public class CourseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner dataLoader(CourseRepository repository) {
        return args -> {
            repository.saveAll(List.of(
                    CourseEntity.builder()
                            .discipline("Java")
                            .description("Best programming language")
                            .build(),
                    CourseEntity.builder()
                            .discipline("Spring")
                            .description("Best course for learning Spring framework")
                            .build(),
                    CourseEntity.builder()
                            .discipline("Spring Microservices")
                            .description("Best microservices course")
                            .build()
            ));
        };
    }
}
