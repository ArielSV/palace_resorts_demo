package com.example.palace_resorts.utils.imageUtils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

fun loadImages(context: Context, urlImage: String, container: ImageView) {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    Glide
        .with(context)
        .load(urlImage)
        .centerCrop()
        .placeholder(circularProgressDrawable)
        .into(container)
}