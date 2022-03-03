package com.example.noticiaspoc.data

import com.example.noticiaspoc.data.model.NewsResponse
import com.example.noticiaspoc.data.model.NewsResponseProvider
import com.example.noticiaspoc.data.network.NewsApiService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApiService,
    private val newsProvider: NewsResponseProvider
) {

    suspend fun getAllNews(): List<NewsResponse> {
        val response = api.getAllNews()
        newsProvider.news = response.getOrNull()!!.getNewsList()

        return newsProvider.news
    }
}