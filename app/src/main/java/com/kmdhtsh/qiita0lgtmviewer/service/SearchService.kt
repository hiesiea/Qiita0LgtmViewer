package com.kmdhtsh.qiita0lgtmviewer.service

import com.kmdhtsh.qiita0lgtmviewer.entity.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    /**
     * 記事の一覧を作成日時の降順で返します。
     * @param page ページ番号 (1から100まで)
     * @param perPage 1ページあたりに含まれる要素数 (1から100まで)
     * @param query 検索クエリ
     */
    @GET("/api/v2/items")
    suspend fun search(
        @Query("page") page: String,
        @Query("per_page") perPage: String,
        @Query("query") query: String
    ): Response<List<Article>>
}
