package com.example.noticiaspoc.features.newsList.domain

import com.example.noticiaspoc.data.NewsRepository
import com.example.noticiaspoc.features.newsList.mapper.NewsResponseModelToNewsUIMapper
import com.example.noticiaspoc.features.newsList.model.NewsUI
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val repository : NewsRepository) {

    suspend operator fun invoke(): List<NewsUI> =
        NewsResponseModelToNewsUIMapper.map(repository.getAllNews())

}