package com.example.noticiaspoc.features.newsList.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noticiaspoc.databinding.ItemNewsListBinding
import com.example.noticiaspoc.features.newsList.model.NewsAdapterModel
import com.example.noticiaspoc.features.newsList.model.NewsListEntity

class NewsAdapter : ListAdapter<NewsAdapterModel, RecyclerView.ViewHolder>(BandDiffCallback()) {

    private var bandList: MutableList<NewsAdapterModel> = mutableListOf();

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
        list?.let { bandList.addAll(list) }
        super.submitList(list)
    }

    override fun getItemCount(): Int = bandList.size

    inner class NewsViewHolder(
        private val binding: ItemNewsListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsAdapterModel) {
            binding.apply {
                news = item
                executePendingBindings()
            }
        }
    }
}

private class BandDiffCallback : DiffUtil.ItemCallback<NewsAdapterModel>() {

    override fun areItemsTheSame(oldItem: NewsAdapterModel, newItem: NewsAdapterModel): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: NewsAdapterModel, newItem: NewsAdapterModel): Boolean {
        return oldItem.content == newItem.content
    }
}