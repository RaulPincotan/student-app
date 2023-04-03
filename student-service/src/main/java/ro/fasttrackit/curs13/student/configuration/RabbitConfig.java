package ro.fasttrackit.curs13.student.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {
    private final ConnectionFactory connectionFactory;

    @Bean
    FanoutExchange studentsExchange() {
        return new FanoutExchange("students.exchange");
    }

    @Bean
    Queue studentsQueue() {
        return new AnonymousQueue();
    }

    @Bean
    Binding studentsBinding(Queue studentsQueue, FanoutExchange stundentsExchange) {
        return BindingBuilder
                .bind(studentsQueue)
                .to(stundentsExchange);
    }

    @Bean
    MessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());

        return template;
    }
}
