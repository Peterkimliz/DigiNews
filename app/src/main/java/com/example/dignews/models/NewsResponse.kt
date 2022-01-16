package com.example.dignews.models

data class NewsResponse(
    val articles:List<Articles>,
    val status:String,
    val totalResults:Int
)
