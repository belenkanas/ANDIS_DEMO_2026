package com.rabbitdemo.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "pedidos.exchange";

    public static final String QUEUE_NORMAL = "pedidos.normal.queue";
    public static final String QUEUE_URGENTE = "pedidos.urgente.queue";

    public static final String RK_NORMAL = "pedidos.normal";
    public static final String RK_URGENTE = "pedidos.urgente";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue pedidosQueue(){

        return new Queue(
            "pedidos.queue",
            true
        );
    }
    
    @Bean
    public Queue normalQueue() {
        return new Queue(QUEUE_NORMAL, true);
    }

    @Bean
    public Queue urgenteQueue() {
        return new Queue(QUEUE_URGENTE, true);
    }

    @Bean
    public Binding bindingNormal() {
        return BindingBuilder.bind(normalQueue())
                .to(exchange())
                .with(RK_NORMAL);
    }

    @Bean
    public Binding bindingUrgente() {
        return BindingBuilder.bind(urgenteQueue())
                .to(exchange())
                .with(RK_URGENTE);
    }
}