package com.algaworks.algaposts.text.processor.service.infraestructure.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FANOUT_EXCHANGE_NAME = "post-processing.post-received.v1.e";
    public static final String QUEUE_TEXT_PROCESSOR = "text-processor-service.post-processing.v1.q";
    public static final String QUEUE_TEXT_PROCESSOR_RESULT = "text-processor-service.post-processing-result.v1.q";

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    public FanoutExchange exchange() {
        return ExchangeBuilder.fanoutExchange(FANOUT_EXCHANGE_NAME).build();
    }

    @Bean
    public Queue queueProcessor() {
        return QueueBuilder.durable(QUEUE_TEXT_PROCESSOR).build();
    }

    @Bean
    public Binding bindingProcessingText() {
        return BindingBuilder.bind(queueProcessor()).to(exchange());
    }

}
