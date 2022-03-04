/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.noticiaspoc.features.newsList.vm

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noticiaspoc.features.newsList.domain.GetAllNewsUseCase
import com.example.noticiaspoc.features.newsList.domain.GetNewsByQueryUseCase
import com.example.noticiaspoc.features.newsList.model.NewsAdapterModel
import com.example.noticiaspoc.features.newsList.model.NewsUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getAllNewsUseCase: GetAllNewsUseCase,
    private val getNewsByQueryUseCase: GetNewsByQueryUseCase
) : ViewModel() {

    val news = MutableLiveData<List<NewsUI>>()

    private val newsClickedMutable: MutableLiveData<NewsAdapterModel> = MutableLiveData()
    val newsClicked: LiveData<NewsAdapterModel>
        get() = newsClickedMutable

    //TODO: Implement progress bar
    val isLoadingVisibility = ObservableInt()

    private val isLoadingMutableLiveData: MutableLiveData<Int> = MutableLiveData()

    init {
        viewModelScope.launch {
            isLoadingMutableLiveData.value = View.VISIBLE
            val result = getAllNewsUseCase()

            if (!result.isNullOrEmpty()) {
                news.postValue(result)
                isLoadingMutableLiveData.value = View.GONE
            }
        }
    }

    fun getNewsByQuery(query: String) {
        viewModelScope.launch {
            isLoadingMutableLiveData.value = View.VISIBLE
            val result = getNewsByQueryUseCase(query)

            if (!result.isNullOrEmpty()) {
                news.postValue(result)
                isLoadingMutableLiveData.value = View.GONE
            }
        }
    }

    //TODO: Save last position to comeback there when returning
    fun onNewsClicked(newsClicked: NewsAdapterModel) {
        newsClickedMutable.value = newsClicked
    }
}
