package com.example.controllers

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

fun Application.configureScrapeController() {
    routing {
        // Create city
        get("/scrape") {
            async {
                scrapeTheData()
            }
        }
    }
}

suspend fun scrapeTheData(): Int = withSpan("my-span") { span ->
    // run some code, maybe add a few attributes
//    HttpClient().use { client ->
//        client.get(builder = HttpRequestBuilder(){
//            host="www.amazon.com"
//        })
//    }
    val price = skrape(HttpFetcher) {
        request {
            url = "https://www.ebay.com/itm/145309888550?hash=item21d524f026"
        }
        response {
            htmlDocument {
                // all official html and html5 elements are supported by the DSL
                div {
                    withClass = "x-price-primary"
                    findFirst {
                        span {
                            withClass = "ux-textspans"
                            findFirst {
                                text
                            }


                        }
                    }
                }
            }
        }
    }
    span.setAttribute("price",price)
    System.out.println(price)
    delay(1000L) // pretend we are doing something useful here
    return@withSpan 13
}
