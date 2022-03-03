package com.example.noticiaspoc.data.network

import com.example.noticiaspoc.data.model.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsApiService @Inject constructor(private val api:NewsAPIClient) {

    suspend fun getNewsDefault(): List<NewsResponse> {
        return withContext(Dispatchers.IO) {
            val response = api.getNews(apiKey, language, sortBy, query)
            response.body() ?: emptyList()
        }
    }

    companion object {
        private const val apiKey = "33bd1eb1c6db43a29c75dab0f3d77519"
        private const val language = "es"
        private const val sortBy = "publishedAt"
        private const val query = "a"
    }
}