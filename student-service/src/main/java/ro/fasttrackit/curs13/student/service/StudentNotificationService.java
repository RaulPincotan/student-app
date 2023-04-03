package ro.fasttrackit.curs13.student.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs13.student.events.StudentEvent;
import ro.fasttrackit.curs13.student.events.StudentEventType;
import ro.fasttrackit.curs13.student.controller.StudentMapper;
import ro.fasttrackit.curs13.student.model.StudentEntity;

@Slf4j
@Service
@RequiredArgsConstructor  //asta e controller de rabbit cum ar veni.. in loc de controller de rest
public class StudentNotificationService {
    private final StudentMapper mapper;
    private final RabbitTemplate rabbitTemplate;


    public void notifyStudentDeleted(StudentEntity studentEntity) {
        rabbitTemplate.convertAndSend(StudentEvent.builder()
                .student(mapper.toApi(studentEntity))
                .type(StudentEventType.DELETED)
                .build());
    }
}
