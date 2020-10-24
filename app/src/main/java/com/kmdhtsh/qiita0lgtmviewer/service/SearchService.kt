package com.kmdhtsh.qiita0lgtmviewer.service

import com.kmdhtsh.qiita0lgtmviewer.entity.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/api/v2/items")
    suspend fun search(
        @Query("page") page: String,
        @Query("per_page") perPage: String,
        @Query("query") query: String
    ): Response<List<Article>>
}
