package com.rabbitdemo.consumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class PedidoConsumer {


    // Flujo básico
    @RabbitListener(queues = "pedidos.queue")
    public void recibir(String msg){

        System.out.println(
            "Pedido recibido: " + msg
        );

    }



    // Routing avanzado
    @RabbitListener(queues = "pedidos.normal.queue")
    public void normal(String msg){

        System.out.println(
            "✓ NORMAL: " + msg
        );

    }



    @RabbitListener(queues = "pedidos.urgente.queue")
    public void urgente(String msg){

        System.out.println(
            "⚠ URGENTE: " + msg
        );

    }

}