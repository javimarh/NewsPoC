package com.example.noticiaspoc.data.network

import android.util.Log
import com.example.noticiaspoc.data.model.NewsResponse
import com.example.noticiaspoc.data.model.NewsResponseAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class NewsApiService @Inject constructor(private val api:NewsAPIClient) {

    suspend fun getNewsDefault(): Result<List<NewsResponse>> {
        return withContext(Dispatchers.IO) {
       //     val response = api.getNews(apiKey, language, sortBy, query)
            val response = api.getNews2()
            response.body() ?: emptyList()
            Result.succ
        }
    }

    suspend fun getAllNews(): Result<NewsResponseAPI> {
            val beers = api.getNews2()
            return Result.success(beers)
    }

    companion object {
        private const val apiKey = "33bd1eb1c6db43a29c75dab0f3d77519"
        private const val language = "es"
        private const val sortBy = "publishedAt"
        private const val query = "a"
    }
}