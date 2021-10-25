package com.example.consumerpoc;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumerpocApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerpocApplication.class, args);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange logExchange() {
        return new DirectExchange("log_exchange");
    }

    @Bean
    public Queue logQueue() {
        return new Queue("log_queue");
    }

    @Bean
    public Binding logQueueToExchangeBinding() {
        return BindingBuilder
                .bind(logQueue())
                .to(logExchange())
                .with("log");
    }
}

