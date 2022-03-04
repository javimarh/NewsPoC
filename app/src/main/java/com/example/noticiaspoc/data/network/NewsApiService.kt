package com.example.noticiaspoc.data.network

import com.example.noticiaspoc.data.model.NewsResponseAPI
import javax.inject.Inject

class NewsApiService @Inject constructor(private val api: NewsAPIClient) {

    suspend fun getAllNews(): Result<NewsResponseAPI> {
        val newsResponse = api.getNews(apiKey, language, sortBy, query)

        return Result.success(newsResponse)
    }

    suspend fun getNewsByQuery(query:String): Result<NewsResponseAPI> {
        val newsResponse = api.getNews(apiKey, language, sortBy, query)

        return Result.success(newsResponse)
    }

    companion object {
        private const val apiKey = "33bd1eb1c6db43a29c75dab0f3d77519"
        private const val language = "es"
        private const val sortBy = "publishedAt"
        private const val query = "a"
    }
}