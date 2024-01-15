package com.example.plugins

import com.example.module
import io.ktor.client.request.*
import io.ktor.server.config.*
import io.ktor.server.testing.*
import kotlin.test.Test

class RoutingKtTest {

    @Test
    fun testGet() = testApplication {
        environment {
            config = MapApplicationConfig("JAVA_TOOL_OPTIONS" to "-javaagent:/var/folders/3h/gkztvvgj7x78v1__lfcl63780000gn/T/digma-otel-jars/opentelemetry-javaagent.jar -Dotel.javaagent.extensions=/var/folders/3h/gkztvvgj7x78v1__lfcl63780000gn/T/digma-otel-jars/digma-otel-agent-extension.jar -Dotel.exporter.otlp.traces.endpoint=http://localhost:5050 -Dotel.resource.attributes=\"digma.environment=RONS-MACBOOK-PRO.LOCAL[LOCAL-TESTS]\" -Dotel.javaagent.experimental.field-injection.enabled=false -Dotel.traces.exporter=otlp -Dotel.metrics.exporter=none -Dotel.instrumentation.experimental.span-suppression-strategy=none -Dotel.service.name=com.example.ktor-sample")
        }
        application {
            module()
        }
        client.get("/").apply {
            assert(true)
        }
    }
}