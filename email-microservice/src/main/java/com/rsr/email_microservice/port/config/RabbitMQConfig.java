package com.rsr.email_microservice.port.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.email.order.queue.name}")
    private String orderQueue;

    @Value("${rabbitmq.email.payment.queue.name}")
    private String paymentQueue;

    @Value("${rabbitmq.email.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.order_email.binding_key}")
    private String orderBindingKey;

    @Value("${rabbitmq.payment_email.binding_key}")
    private String paymentBindingKey;


    @Bean
    public Queue orderQueue() {
        return new Queue(orderQueue);
    }

    @Bean
    public Queue paymentQueue() {
        return new Queue(paymentQueue);
    }


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding bindingOrder() {
        return BindingBuilder
                .bind(orderQueue())
                .to(exchange())
                .with(orderBindingKey);
    }

    @Bean
    public Binding bindingPayment() {
        return BindingBuilder
                .bind(paymentQueue())
                .to(exchange())
                .with(paymentBindingKey);
    }


    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}
