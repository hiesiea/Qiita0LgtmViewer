package com.kmdhtsh.qiita0lgtmviewer.mock

import com.kmdhtsh.qiita0lgtmviewer.entity.Article
import com.kmdhtsh.qiita0lgtmviewer.service.SearchService
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate

/**
 * SearchServiceのモック
 */
class MockSearchService(
    private val delegate: BehaviorDelegate<SearchService>,
) : SearchService {
    var response: List<Article>? = null

    override suspend fun search(
        page: String,
        perPage: String,
        query: String
    ): Response<List<Article>> {
        return delegate.returningResponse(response).search(page, perPage, query)
    }
}
