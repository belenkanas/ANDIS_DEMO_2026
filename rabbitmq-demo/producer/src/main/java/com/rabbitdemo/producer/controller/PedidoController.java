package com.rabbitdemo.producer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitdemo.producer.service.PedidoProducer;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {


    private final PedidoProducer producer;


    public PedidoController(PedidoProducer producer){
        this.producer = producer;
    }


    // Flujo general
    @PostMapping("/enviar")
    public String enviar(@RequestBody String msg){

        producer.enviarPedido(msg);

        return "Pedido enviado a RabbitMQ";
    }


    // Routing avanzado
    @PostMapping("/normal")
    public String normal(@RequestBody String msg){

        producer.enviarNormal(msg);

        return "OK NORMAL";
    }


    @PostMapping("/urgente")
    public String urgente(@RequestBody String msg){

        producer.enviarUrgente(msg);

        return "OK URGENTE";
    }
}