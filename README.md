# RabbitMQ Demo - Producer / Consumer

Demo de comunicación asíncrona utilizando **RabbitMQ** como broker de mensajería, implementada con **Spring Boot** y ejecutada mediante **Docker**.

La solución está compuesta por:

* **Producer:** aplicación Spring Boot que expone un endpoint REST y permite enviar mensajes hacia RabbitMQ.
* **RabbitMQ:** broker encargado de administrar el intercambio y distribución de mensajes mediante colas.
* **Consumer:** aplicación Spring Boot que escucha las colas y procesa los mensajes recibidos.

---

# Arquitectura - Flujo de comunicación

```text
Cliente (Postman)
        |
        v
Producer (Spring Boot)
        |
        v
RabbitMQ (Exchange / Queue)
        |
        v
Consumer (Spring Boot)
        |
        v
Procesamiento del mensaje
```

RabbitMQ permite desacoplar el productor del consumidor, permitiendo que ambos servicios funcionen de forma independiente mediante comunicación asíncrona y escalable.

La demo incluye el uso de **Direct Exchange** para enrutar mensajes mediante **routing keys**, permitiendo separar distintos tipos de pedidos (por ejemplo, normales y urgentes) hacia colas específicas para su procesamiento.

---

# Componentes principales

## Producer

Aplicación Spring Boot encargada de enviar mensajes a RabbitMQ.

Componentes principales:

* `PedidoController`

  * Expone endpoints REST para recibir pedidos.
  * Recibe el mensaje enviado desde Postman.

* `PedidoProducer`

  * Utiliza `RabbitTemplate` para enviar mensajes hacia RabbitMQ.

Ejemplo de endpoint:

```http
POST http://localhost:8081/pedidos/enviar
```

Body:

```json
"Pedido de prueba"
```

Este mensaje es enviado a RabbitMQ para ser procesado por el consumidor.

---

## Consumer

Aplicación Spring Boot encargada de escuchar y procesar mensajes.

Componentes principales:

* `PedidoConsumer`

  * Utiliza `@RabbitListener`.
  * Se mantiene escuchando la cola configurada.
  * Procesa los mensajes recibidos.

Ejemplo de recepción:

```text
Pedido recibido: Pedido de prueba
✓ NORMAL: Pedido de prueba (normal)
⚠ URGENTE: Pedido de prueba (urgente)
```

* `RabbitConfig`

  * Define la configuración de RabbitMQ.
  * Declara colas, exchanges y bindings necesarios.

---

# Instalación y ejecución

## 1. Levantar RabbitMQ con Docker

Ejecutar:

```bash
docker run -it --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management
```

RabbitMQ quedará disponible en:

```
http://localhost:15672
```

Credenciales:

```
usuario: guest
password: guest
```

Desde la interfaz web se pueden visualizar:

* Exchanges
* Queues
* Mensajes enviados y consumidos

---

## 2. Ejecutar Consumer

Ingresar al proyecto:

```bash
cd consumer
```

Ejecutar:

```bash
./mvnw spring-boot:run
```

El Consumer debe iniciarse primero para comenzar a escuchar la cola.

---

## 3. Ejecutar Producer

Ingresar al proyecto:

```bash
cd producer
```

Ejecutar:

```bash
./mvnw spring-boot:run
```

El Producer queda disponible en:

```
http://localhost:8081
```

---

# Prueba de funcionamiento

Enviar un mensaje desde Postman:

```
POST http://localhost:8081/pedidos/normal
```

Body:

```json
"Hola RabbitMQ"
```

Resultado esperado en la consola del Consumer:

```text
✓ NORMAL: Hola RabbitMQ
```

---

# Routing con Exchange

La demo utiliza un **Direct Exchange** para enrutar mensajes mediante **routing keys**.

Dependiendo del tipo de pedido, el mensaje puede dirigirse a diferentes colas:

* `pedidos.normal`
* `pedidos.urgente`

Esto permite separar el procesamiento según la prioridad del mensaje.

---

# Tecnologías utilizadas

* Java 17+
* Spring Boot
* Spring AMQP
* RabbitMQ
* Docker
* Maven
