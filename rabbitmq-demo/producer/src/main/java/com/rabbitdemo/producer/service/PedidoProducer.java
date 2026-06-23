package com.rabbitdemo.producer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidoProducer {

    private final RabbitTemplate rabbitTemplate;

    public PedidoProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    // Flujo general
    public void enviarPedido(String msg){

        rabbitTemplate.convertAndSend(
                "pedidos.queue",
                msg
        );
    }



    // Routing avanzado
    public void enviarNormal(String msg){
        rabbitTemplate.convertAndSend(
                "pedidos.exchange",
                "pedidos.normal",
                msg
        );
    }

    public void enviarUrgente(String msg){
        rabbitTemplate.convertAndSend(
                "pedidos.exchange",
                "pedidos.urgente",
                msg
        );
    }
}