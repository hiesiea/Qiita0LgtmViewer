package com.kmdhtsh.qiita0lgtmviewer.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 記事データ
 * JSONのキー名に合わせている
 * @param id 記事の一意なID
 * @param title 記事のタイトル
 * @param likes_count この記事への「LGTM！」の数（Qiitaでのみ有効）
 * @param url 記事のURL
 * @param user Qiita上のユーザを表します。
 */
@Parcelize
data class Article(
    val id: String,
    val title: String,
    val likes_count: Int,
    val url: String,
    val user: User
) : Parcelable
