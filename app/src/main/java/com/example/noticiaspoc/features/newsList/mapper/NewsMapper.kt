package com.example.noticiaspoc.features.newsList.mapper

import com.example.noticiaspoc.data.model.NewsResponse
import com.example.noticiaspoc.features.newsList.model.NewsAdapterModel
import com.example.noticiaspoc.features.newsList.model.NewsUI

object NewsResponseModelToNewsUIMapper {
    fun map(type: List<NewsResponse>): List<NewsUI> {
        return type.map {
            NewsUI(
                it.title,
                it.description,
                it.url,
                it.urlToImage,
                it.publishedAt,
                it.content
            )
        }
    }
}

object NewsUIToAdapterModelMapper {
    fun map(type: List<NewsUI>): List<NewsAdapterModel> {
        return type.map {
            NewsAdapterModel(
                it.title,
                it.description,
                it.url,
                it.urlToImage,
                it.publishedAt,
                it.content
            )
        }
    }
}

object NewsAdapterModelToNewsUIMapper {
    fun map(type: NewsAdapterModel): NewsUI {
        return NewsUI(
            type.title,
            type.description,
            type.url,
            type.urlToImage,
            type.publishedAt,
            type.content
        )
    }
}