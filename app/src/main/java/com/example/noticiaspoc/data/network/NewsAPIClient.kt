package com.example.noticiaspoc.data.network

import com.example.noticiaspoc.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIClient {
    @GET("everything?")
    suspend fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("language") language: String,
        @Query("sortBy") sortBy: String,
        @Query("query") query: String
    ): Response<List<NewsResponse>>
}