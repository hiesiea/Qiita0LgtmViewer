package com.kmdhtsh.qiita0lgtmviewer.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val id: String, val name: String, val profile_image_url: String) : Parcelable
