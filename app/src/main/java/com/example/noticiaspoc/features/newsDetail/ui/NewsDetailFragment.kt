package com.example.noticiaspoc.features.newsList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.noticiaspoc.R
import com.example.noticiaspoc.databinding.FragmentNewsDetailBinding
import com.example.noticiaspoc.util.loadImage

import com.example.noticiaspoc.util.NEWS_TITLE
import com.example.noticiaspoc.util.NEWS_DESCRIPTION
import com.example.noticiaspoc.util.NEWS_DATE
import com.example.noticiaspoc.util.NEWS_URLTOIMAGE

class NewsDetailFragment : Fragment() {

    lateinit var binding: FragmentNewsDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        context ?: return binding.root
        bindViews()
        initBackListener()

        return binding.root
    }

    private fun bindViews() {
        arguments?.let {
            binding.apply {
                newsDetailImage.loadImage(it.getString(NEWS_URLTOIMAGE, ""))
                newsDetailTitle.text = it.getString(NEWS_TITLE)
                newsDetailDate.text = it.getString(NEWS_DATE)
                newsDetailDescription.text = it.getString(NEWS_DESCRIPTION)
            }
        }
    }

    //TODO: Fix this &#&!#!
    private fun initBackListener() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    view?.findNavController()?.navigate(R.id.detail_to_news_list, null)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}

