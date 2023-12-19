package com.example.palace_resorts.utils.imageUtils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.palace_resorts.R

fun loadImages(context: Context, urlImage: String, container: ImageView) {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    Glide
        .with(context)
        .load(urlImage)
        .centerCrop()
        .error(R.mipmap.image_placeholder)
        .placeholder(circularProgressDrawable)
        .into(container)
}