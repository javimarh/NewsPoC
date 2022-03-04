package com.example.noticiaspoc.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.noticiaspoc.R

fun ImageView.loadListImage(imageUri: String) {
    Glide.with(context)
        .load(imageUri)
        .error(R.drawable.outline_image_24)
        .override(250, 500)
        .into(this)
}

fun ImageView.loadImage(imageUri: String) {
    Glide.with(context)
        .load(imageUri)
        .error(R.drawable.outline_image_24)
        .into(this)
}