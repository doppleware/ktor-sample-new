package com.example.dtos

import kotlinx.serialization.Serializable

@Serializable
data class PriceRecordDto(
    val price: String,
    val website: String,
)

@Serializable
data class WebsiteRecordDtos(
    val name: String,
    val url: String,
)

@Serializable
data class ScrapeRequest (val url:String, val name: String)

