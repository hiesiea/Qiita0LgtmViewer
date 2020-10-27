package com.kmdhtsh.qiita0lgtmviewer.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Qiita上のユーザデータ
 * JSONのキー名に合わせている
 * @param id ユーザID
 * @param name 設定している名前
 * @param profile_image_url 設定しているプロフィール画像のURL
 */
@Parcelize
data class User(val id: String, val name: String, val profile_image_url: String) : Parcelable
