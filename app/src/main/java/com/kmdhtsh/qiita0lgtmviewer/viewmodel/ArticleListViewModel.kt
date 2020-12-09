package com.kmdhtsh.qiita0lgtmviewer.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmdhtsh.qiita0lgtmviewer.entity.Article
import com.kmdhtsh.qiita0lgtmviewer.repository.SearchRepository
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * 記事表示用ViewModel
 */
class ArticleListViewModel @ViewModelInject constructor(
    private val searchRepository: SearchRepository
) :
    ViewModel() {
    // 記事一覧（読み書き用）
    // MutableLiveDataだと受け取った側でも値を操作できてしまうので、読み取り用のLiveDataも用意しておく
    private val _articleList = MutableLiveData<Result<List<Article>>>()
    val articleList: LiveData<Result<List<Article>>> = _articleList

    /**
     * 検索処理
     * @param page ページ番号 (1から100まで)
     * @param perPage 1ページあたりに含まれる要素数 (1から100まで)
     * @param query 検索クエリ
     */
    fun search(page: Int, perPage: Int, query: String) = viewModelScope.launch {
        try {
            Timber.d("search start")
            val response = searchRepository.search(page.toString(), perPage.toString(), query)

            // Responseに失敗しても何かしら返す
            val result = if (response.isSuccessful) {
                response.body() ?: mutableListOf()
            } else {
                mutableListOf()
            }

            // LGTM数0の記事だけに絞る
            val filteredResult = result.filter {
                it.likes_count == 0
            }

            // viewModelScopeはメインスレッドなので、setValueで値をセットする
            _articleList.value = Result.success(filteredResult)
            Timber.d("search finish")
        } catch (e: Throwable) {
            _articleList.value = Result.failure(e)
        }
    }
}
