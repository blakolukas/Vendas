package com.projarq.registrovendas.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    public static final String QUEUE_NAME = 
        "vendas.efetivadas.queue." + Math.round(Math.random() * 1000);

    @Bean
    public Queue vendaEfetivadaQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    public String getQueueName() {
        return QUEUE_NAME;
    }
}

