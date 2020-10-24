package com.kmdhtsh.qiita0lgtmviewer

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind:imageUrl")
fun ImageView.loadImage(url: String) {
    Glide.with(context).load(url).into(this)
}
