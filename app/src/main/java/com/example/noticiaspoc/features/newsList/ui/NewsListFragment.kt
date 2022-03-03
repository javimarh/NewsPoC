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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

@AndroidEntryPoint
class NewsListFragment : Fragment(), SearchView.OnQueryTextListener {

    lateinit var binding: FragmentNewsListBinding
//    private val viewModel: NewsListViewModel by viewModels {
//        NewsListViewModel.NewsListViewModelFactory((this.activity?.application as MainApplication).repository)
//    }

    private val viewModel: NewsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewsListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = NewsAdapter()
//        val songAdapter = SongAdapter()
        binding.newsRecyclerView.adapter = adapter
        initObservers(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun initObservers(adapter: NewsAdapter) {
        viewModel.news.observe(viewLifecycleOwner) { news ->
            initNews(news)
        }
    }

    override fun onQueryTextSubmit(query: String): Boolean {
//        searchByName(query.lowercase(Locale.getDefault()))
        return true
    }

//    private fun searchByName(query: String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            val call = getRetrofit().create(NewsAPIClient::class.java).getNews()
//            val news = call.body() as NewsResponse
//            if (call.isSuccessful) {
////                initNews(news)
//            } else {
//                //show error
//            }
//        }
//        hideKeyboard()
//    }

    private fun initNews(newsResponse: List<NewsUI>) {
        val newsAdapter = NewsAdapter()
        binding.newsRecyclerView.apply {
            adapter = newsAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }
        newsAdapter.submitList(NewsUIToAdapterModelMapper.map(newsResponse))
    }

    private fun hideKeyboard() {
        val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}
