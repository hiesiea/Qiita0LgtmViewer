package com.kmdhtsh.qiita0lgtmviewer.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(val id: String, val title: String, val url: String, val user: User) : Parcelable
