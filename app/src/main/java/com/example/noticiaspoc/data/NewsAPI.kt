package com.example.noticiaspoc.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsAPI {
    @GET
    suspend fun getNews(@Url url: String): Response<NewsResponse>
}