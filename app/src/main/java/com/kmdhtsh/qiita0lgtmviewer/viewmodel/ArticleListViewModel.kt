package com.kmdhtsh.qiita0lgtmviewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmdhtsh.qiita0lgtmviewer.entity.Article
import com.kmdhtsh.qiita0lgtmviewer.repository.SearchRepository
import kotlinx.coroutines.launch

class ArticleListViewModel(private val searchRepository: SearchRepository) : ViewModel() {
    private val _articleList = MutableLiveData<Result<List<Article>>>()
    val articleList: LiveData<Result<List<Article>>> = _articleList

    fun search(page: Int, perPage: Int, query: String) = viewModelScope.launch {
        try {
            val response = searchRepository.search(page.toString(), perPage.toString(), query)
            val result = if (response.isSuccessful) {
                response.body()!!
            } else {
                mutableListOf()
            }
            // LGTM数が0の記事だけに絞る
            val filteredResult = result.filter {
                it.likes_count == 0
            }
            _articleList.postValue(Result.success(filteredResult))
        } catch (e: Throwable) {
            _articleList.postValue(Result.failure(e))
        }
    }
}
