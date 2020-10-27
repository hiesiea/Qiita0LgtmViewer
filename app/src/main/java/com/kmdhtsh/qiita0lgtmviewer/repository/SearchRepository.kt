package com.kmdhtsh.qiita0lgtmviewer.repository

import com.kmdhtsh.qiita0lgtmviewer.entity.Article
import com.kmdhtsh.qiita0lgtmviewer.service.SearchService
import retrofit2.Response
import javax.inject.Inject

/**
 * 検索用Repository
 */
class SearchRepository @Inject constructor(private val searchService: SearchService) {

    /**
     * 検索処理
     * @param page ページ番号 (1から100まで)
     * @param perPage 1ページあたりに含まれる要素数 (1から100まで)
     * @param query 検索クエリ
     */
    suspend fun search(page: String, perPage: String, query: String): Response<List<Article>> {
        return searchService.search(page, perPage, query)
    }
}
