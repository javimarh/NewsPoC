package com.example.noticiaspoc.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponseAPI(
    var status: String,
    @SerializedName("articles") var news: List<NewsResponse>,
) {
    fun getNewsList(): List<NewsResponse> {
        return news
    }
}