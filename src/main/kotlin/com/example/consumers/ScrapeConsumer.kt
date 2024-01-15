package com.example.consumers

import com.example.contract.ScrapingTarget
import com.example.tracing.withSpan
import io.ktor.server.application.*
import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.selects.html5.div
import it.skrape.selects.html5.span
import kotlinx.coroutines.async
import pl.jutupe.ktor_rabbitmq.consume
import pl.jutupe.ktor_rabbitmq.rabbitConsumer

fun Application.configureScapeConsumer() {
    rabbitConsumer {
        consume<ScrapingTarget>("queue") { body ->
            async {
                scrapePrice(body.url)
            }
        }
    }
}

suspend fun scrapePrice(scrapeUrl: String): String = withSpan("scrape website for price") { span ->

    val price = skrape(HttpFetcher) {
        request {
            this.url = scrapeUrl
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
    price
}

