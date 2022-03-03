package com.example.noticiaspoc.features.newsList.ui

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noticiaspoc.MainApplication
import kotlinx.coroutines.CoroutineScope
import com.example.noticiaspoc.data.network.NewsAPIClient
import com.example.noticiaspoc.data.model.NewsResponse
import com.example.noticiaspoc.databinding.FragmentNewsListBinding
import com.example.noticiaspoc.features.newsList.mapper.NewsUIToAdapterModelMapper
import com.example.noticiaspoc.features.newsList.model.NewsUI
import com.example.noticiaspoc.features.newsList.vm.NewsListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class NewsDetailFragment : Fragment() {


}
