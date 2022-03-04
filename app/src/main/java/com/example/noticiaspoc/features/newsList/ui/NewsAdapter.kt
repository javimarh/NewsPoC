package com.example.noticiaspoc.features.newsList.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noticiaspoc.databinding.ItemNewsListBinding
import com.example.noticiaspoc.features.newsList.model.NewsAdapterModel
import com.example.noticiaspoc.features.newsList.vm.NewsListViewModel
import com.example.noticiaspoc.util.loadListImage

class NewsAdapter(private val viewModel: NewsListViewModel) :
    ListAdapter<NewsAdapterModel, RecyclerView.ViewHolder>(NewsDiffCallback()) {

    private var newsList: MutableList<NewsAdapterModel> = mutableListOf();


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return NewsViewHolder(
            ItemNewsListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item is NewsAdapterModel)
            (holder as NewsViewHolder).bind(item)
    }

    override fun submitList(list: List<NewsAdapterModel>?) {
        list?.let { newsList.addAll(list) }
        super.submitList(list)
    }

    override fun getItemCount(): Int = newsList.size

    inner class NewsViewHolder(
        private val binding: ItemNewsListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsAdapterModel) {
            binding.apply {
                news = item
                itemNewsListLayout.setOnClickListener { viewModel.onNewsClicked(item) }
                item.urlToImage?.let {
                    itemNewsListImage.loadListImage(item.urlToImage)
                }
                executePendingBindings()
            }
        }
    }
}

private class NewsDiffCallback : DiffUtil.ItemCallback<NewsAdapterModel>() {

    override fun areItemsTheSame(oldItem: NewsAdapterModel, newItem: NewsAdapterModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: NewsAdapterModel, newItem: NewsAdapterModel): Boolean {
        return oldItem.description == newItem.description
    }
}