package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import pl.jutupe.ktor_rabbitmq.consume
import pl.jutupe.ktor_rabbitmq.publish
import pl.jutupe.ktor_rabbitmq.rabbitConsumer

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/queue"){
                call.publish("exchange", "routingKey", null, MyObject("test name"))

        }
    }
    rabbitConsumer {
        consume<MyObject>("queue") { body ->
            println("Consumed message $body")
        }
    }
}

data class MyObject(val s: String)
