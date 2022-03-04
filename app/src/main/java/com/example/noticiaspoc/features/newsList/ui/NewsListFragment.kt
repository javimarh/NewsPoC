package com.example.noticiaspoc.features.newsList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noticiaspoc.R
import com.example.noticiaspoc.databinding.FragmentNewsListBinding
import com.example.noticiaspoc.features.newsList.mapper.NewsUIToAdapterModelMapper
import com.example.noticiaspoc.features.newsList.model.NewsAdapterModel
import com.example.noticiaspoc.features.newsList.model.NewsUI
import com.example.noticiaspoc.features.newsList.vm.NewsListViewModel
import com.example.noticiaspoc.util.NEWS_DATE
import com.example.noticiaspoc.util.NEWS_DESCRIPTION
import com.example.noticiaspoc.util.NEWS_TITLE
import com.example.noticiaspoc.util.NEWS_URLTOIMAGE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    lateinit var binding: FragmentNewsListBinding

    private val viewModel: NewsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        context ?: return binding.root
        initObservers()
        initSearchingListener()

        return binding.root
    }

    private fun initObservers() {
        viewModel.news.observe(viewLifecycleOwner) { news ->
            initNews(news)
        }

        viewModel.newsClicked.observe(viewLifecycleOwner) { newsSelected ->
            openNewsDetail(newsSelected)
        }
    }

    private fun initNews(newsResponse: List<NewsUI>) {
        val newsAdapter = NewsAdapter(viewModel)
        binding.newsRecyclerView.apply {
            adapter = newsAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }
        newsAdapter.submitList(NewsUIToAdapterModelMapper.map(newsResponse))
    }

    private fun openNewsDetail(newsSelected: NewsAdapterModel) {
        val bundle = bundleOf(
            NEWS_URLTOIMAGE to newsSelected.urlToImage,
            NEWS_TITLE to newsSelected.title,
            NEWS_DATE to newsSelected.publishedAt.dropLast(10),
            NEWS_DESCRIPTION to newsSelected.description
        )

        view?.findNavController()?.navigate(R.id.home_to_news_detail, bundle)
    }

    private fun initSearchingListener() {
        binding.newsSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.getNewsByQuery(query) }

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })

    }
}
