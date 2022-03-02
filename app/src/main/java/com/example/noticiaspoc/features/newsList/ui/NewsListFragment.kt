package com.example.noticiaspoc.features.newsList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.javichordskotlin.MainApplication
import com.example.javichordskotlin.databinding.FragmentSongListBinding
import com.example.javichordskotlin.ui.songList.vm.SongListViewModel

class NewsListFragment : Fragment() {

    //    private val viewModel: SongListViewModel by viewModels()
    private val viewModel: SongListViewModel by viewModels {
        SongListViewModel.SongListViewModelFactory((this.activity?.application as MainApplication).repository)
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
}
