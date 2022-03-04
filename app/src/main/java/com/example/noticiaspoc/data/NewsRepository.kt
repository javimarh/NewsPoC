package com.example.noticiaspoc.data

import com.example.noticiaspoc.data.model.NewsResponseModel
import com.example.noticiaspoc.data.model.NewsResponseProvider
import com.example.noticiaspoc.data.network.NewsApiService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApiService,
    private val newsProvider: NewsResponseProvider
) {
    //TODO: Handle empty results

    suspend fun getAllNews(): List<NewsResponseModel> {
        val response = api.getAllNews()

        newsProvider.news = response.getOrNull()!!.getNewsList()
        return newsProvider.news
    }

    suspend fun getNewsByQuery(query: String): List<NewsResponseModel> {
        val response = api.getNewsByQuery(query)

        newsProvider.news = response.getOrNull()!!.getNewsList()
        return newsProvider.news
    }
}