package com.example.noticiaspoc.data

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("url") var url: String,
    @SerializedName("urlToImage") var urlToImage: String,
    @SerializedName("publishedAt") var publishedAt: String,
    @SerializedName("content") var images: String,
)