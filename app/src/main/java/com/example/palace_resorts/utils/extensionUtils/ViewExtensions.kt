package com.example.palace_resorts.utils.extensionUtils

import android.view.View
import android.view.ViewGroup

fun View.removeFromParent() {
    this.parent?.let {
        (it as ViewGroup).removeView(this)
    }
}

fun Int?.orZero(): Int = this ?: 0

fun Boolean?.orFalse() = this ?: false

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}