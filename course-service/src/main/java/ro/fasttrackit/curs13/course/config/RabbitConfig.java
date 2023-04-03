package ro.fasttrackit.curs13.course.config;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

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
}
