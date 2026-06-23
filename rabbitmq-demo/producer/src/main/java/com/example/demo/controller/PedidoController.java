package com.example.producer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.producer.service.PedidoProducer;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {


    private final PedidoProducer producer;


    public PedidoController(PedidoProducer producer){
        this.producer = producer;
    }


    @PostMapping
    public String crearPedido(
            @RequestBody String pedido
    ){

        producer.enviarPedido(pedido);

        return "Pedido enviado a RabbitMQ";
    }
}