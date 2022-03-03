package com.example.noticiaspoc.features.newsList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.coroutines.CoroutineScope
import com.example.noticiaspoc.data.NewsAPI
import com.example.noticiaspoc.features.newsList.vm.NewsListViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsListFragment : Fragment() {

    //    private val viewModel: SongListViewModel by viewModels()
    private val viewModel: NewsListViewModel by viewModels {
        NewsListViewModel.NewsListViewModelFactory((this.activity?.application as MainApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSongListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = BandAdapter()
//        val songAdapter = SongAdapter()
        binding.bandList.adapter = adapter
        initObservers(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun initObservers(adapter: BandAdapter) {
        viewModel.bands.observe(viewLifecycleOwner) { bands ->
            adapter.submitList(bands)
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/everything/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(NewsAPI::class.java).getNews("$query/images")
        }
    }
}
