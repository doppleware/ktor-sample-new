
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

val postgres_version: String by project
val h2_version: String by project
plugins {
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.3.7"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")

}

dependencies {
    implementation ("io.ktor:ktor-serialization-jackson")
    implementation ("com.github.JUtupe:ktor-rabbitmq:0.4.0")
    implementation ("com.rabbitmq:amqp-client:5.20.0")
    implementation("io.opentelemetry:opentelemetry-extension-kotlin:1.29.0")
    implementation("io.opentelemetry.instrumentation:opentelemetry-ktor-2.0:1.29.0-alpha")
    implementation("io.opentelemetry:opentelemetry-api:1.29.0")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-client")
    implementation("it.skrape:skrapeit:1.3.0-alpha.1")
    implementation("io.ktor:ktor-client-cio")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("org.postgresql:postgresql:$postgres_version")
    implementation("com.h2database:h2:$h2_version")
    implementation("io.ktor:ktor-serialization-jackson-jvm")
    implementation("io.ktor:ktor-server-swagger-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testImplementation("io.ktor:ktor-server-test-host-jvm:2.3.7")
}
