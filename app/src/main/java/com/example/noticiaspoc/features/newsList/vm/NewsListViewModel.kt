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

import androidx.lifecycle.*
import com.example.javichordskotlin.data.Band
import com.example.javichordskotlin.data.BandWSongs
import com.example.javichordskotlin.data.repo.BandRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject internal constructor(
    bandRepository: BandRepository
) : ViewModel() {

    private val status: MutableStateFlow<Int> = MutableStateFlow(
        STATUS.length
    )

    val bands: LiveData<List<BandWSongs>> = status.flatMapLatest {
        bandRepository.getBandsWSongs()
    }.asLiveData()

    init {
        viewModelScope.launch {
            status.collect {
//                savedStateHandle.set(STATUS, it)
            }
        }

        fun insert(band: Band) = viewModelScope.launch {
            bandRepository.insertBand(band)
        }
    }

    class SongListViewModelFactory(private val repository: BandRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NewsListViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    companion object {
        private const val ERROR = -1
        private const val LOADING = 1
        private const val SUCCEED = 2
        private const val STATUS = "STATUS"
    }
}
