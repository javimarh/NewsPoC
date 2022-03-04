package com.example.noticiaspoc.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsResponseProvider @Inject constructor(){
    var news: List<NewsResponseModel> = emptyList()
}