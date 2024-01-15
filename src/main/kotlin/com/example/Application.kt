package com.example

import com.example.consumers.configureScapeConsumer
import com.example.controllers.configureScrapeController
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.OpenTelemetry
import io.opentelemetry.instrumentation.ktor.v2_0.server.KtorServerTracing


fun main() {

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

//    val openTelemetry: OpenTelemetry = GlobalOpenTelemetry.get()
//
//    install(KtorServerTracing){
//        setOpenTelemetry(openTelemetry)
//    }

    configureRabbitMQ()
    configureSerialization()
    configureDatabases()
    configureHTTP()
    configureRouting()
    configureScrapeController()
    configureScapeConsumer()
}
