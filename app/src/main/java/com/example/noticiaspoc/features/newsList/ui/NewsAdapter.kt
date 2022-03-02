package com.example.noticiaspoc.features.newsList.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.javichordskotlin.data.BandWSongs
import com.example.javichordskotlin.data.Song
import com.example.javichordskotlin.databinding.ItemBandListBinding

class BandAdapter : ListAdapter<BandWSongs, RecyclerView.ViewHolder>(BandDiffCallback()) {

    private var bandList: MutableList<BandWSongs> = mutableListOf();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return BandViewHolder(
            ItemBandListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item is BandWSongs)
            (holder as BandViewHolder).bind(item)
    }

    override fun submitList(list: List<BandWSongs>?) {
        list?.let { bandList.addAll(list) }
        super.submitList(list)
    }

    override fun getItemCount(): Int = bandList.size

    inner class BandViewHolder(
        private val binding: ItemBandListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BandWSongs) {
            binding.apply {
                band = item
                adapter = SongAdapter(item.songList)
                executePendingBindings()
            }
        }
    }
}

private class BandDiffCallback : DiffUtil.ItemCallback<BandWSongs>() {

    override fun areItemsTheSame(oldItem: BandWSongs, newItem: BandWSongs): Boolean {
        return oldItem.band.id == newItem.band.id
    }

    override fun areContentsTheSame(oldItem: BandWSongs, newItem: BandWSongs): Boolean {
        return oldItem == newItem
    }
}