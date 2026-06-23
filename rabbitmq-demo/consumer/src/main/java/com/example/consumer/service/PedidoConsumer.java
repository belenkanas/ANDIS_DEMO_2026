package com.example.consumer.service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PedidoConsumer {


    @RabbitListener(queues = "pedidos.queue")
    public void recibir(String mensaje){

        System.out.println(
          "Pedido recibido: " + mensaje
        );

    }

}