package com.example.noticiaspoc.data.network

import com.example.noticiaspoc.data.model.NewsResponse
import com.example.noticiaspoc.data.model.NewsResponseAPI
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIClient {
    @GET("everything?q=a&language=es&sortBy=publishedAt&apiKey=33bd1eb1c6db43a29c75dab0f3d77519")
    suspend fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("language") language: String,
        @Query("sortBy") sortBy: String,
        @Query("q") query: String
    ): Response<List<NewsResponse>>

    @GET("everything?q=a&language=es&sortBy=publishedAt&apiKey=33bd1eb1c6db43a29c75dab0f3d77519")
    suspend fun getNews2(
    ): NewsResponseAPI
}