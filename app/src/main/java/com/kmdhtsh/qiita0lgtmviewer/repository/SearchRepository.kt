package com.kmdhtsh.qiita0lgtmviewer.repository

import com.kmdhtsh.qiita0lgtmviewer.entity.Article
import com.kmdhtsh.qiita0lgtmviewer.service.SearchService
import retrofit2.Response

class SearchRepository(private val searchService: SearchService) {
    suspend fun search(page: String, perPage: String, query: String): Response<List<Article>> {
        return searchService.search(page, perPage, query)
    }
}
