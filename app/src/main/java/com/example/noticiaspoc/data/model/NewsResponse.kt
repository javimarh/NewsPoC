package com.example.noticiaspoc.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    var status: Int,
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("url") var url: String,
    @SerializedName("urlToImage") var urlToImage: String,
    @SerializedName("publishedAt") var publishedAt: String,
    @SerializedName("content") var content: String,
)