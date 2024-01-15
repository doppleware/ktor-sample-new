package com.example.controllers

import com.example.contract.ScrapingTarget
import com.example.plugins.City
import com.example.tracing.withSpan
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.matchers.toContain
import it.skrape.selects.html5.a
import it.skrape.selects.html5.div
import it.skrape.selects.html5.span
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
//import io.ktor.client.*
//import io.ktor.client.request.*

import kotlinx.coroutines.launch
import pl.jutupe.ktor_rabbitmq.publish

fun Application.configureScrapeController() {
    routing {
        // Create city
        get("/scrape") {
            call.publish("exchange", "routingKey", null, ScrapingTarget("https://www.ebay.com/itm/145309888550?hash=item21d524f026"))
        }
    }
}

