package com.example.noticiaspoc.features.newsList.model

data class NewsAdapterModel(
    val title: String,
    val description: String,
    val urlToImage: String?,
    val publishedAt: String,
)