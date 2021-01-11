package com.kmdhtsh.qiita0lgtmviewer.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.kmdhtsh.qiita0lgtmviewer.LiveDataTestUtil
import com.kmdhtsh.qiita0lgtmviewer.entity.Article
import com.kmdhtsh.qiita0lgtmviewer.entity.User
import com.kmdhtsh.qiita0lgtmviewer.mock.MockSearchService
import com.kmdhtsh.qiita0lgtmviewer.repository.SearchRepository
import com.kmdhtsh.qiita0lgtmviewer.service.SearchService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import java.util.concurrent.TimeUnit

/**
 * ArticleListViewModelのテストコード
 */
@ExperimentalCoroutinesApi
class ArticleListViewModelTest {

    // LiveDataをテストするために必要
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://qiita.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    private val behavior = NetworkBehavior.create()
    private val delegate = MockRetrofit.Builder(retrofit).networkBehavior(behavior).build()
        .create(SearchService::class.java)
    private val searchService = MockSearchService(delegate)
    private val viewModel = ArticleListViewModel(SearchRepository(searchService))

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun search_0LGTMの記事あり() {
        behavior.apply {
            setDelay(0, TimeUnit.MILLISECONDS) // 即座に結果が返ってくるようにする
            setVariancePercent(0)
            setFailurePercent(0)
            setErrorPercent(0)
        }
        val articleList = listOf(
            Article("", "", 0, "", User("", "", ""))
        )
        searchService.response = articleList
        viewModel.search(1, 1, "")
        val result = LiveDataTestUtil.getValue(viewModel.articleList)

        // 成功扱いか
        assertThat(result?.isSuccess).isTrue()

        // データが存在するか
        assertThat(result?.getOrNull()).isNotNull()

        // データが1件以上存在するか
        assertThat(result?.getOrNull()).isNotEmpty()
    }

    @Test
    fun search_0LGTMの記事なし() {
        behavior.apply {
            setDelay(0, TimeUnit.MILLISECONDS) // 即座に結果が返ってくるようにする
            setVariancePercent(0)
            setFailurePercent(0)
            setErrorPercent(0)
        }
        val articleList = listOf(
            Article("", "", 1, "", User("", "", ""))
        )
        searchService.response = articleList
        viewModel.search(1, 1, "")
        val result = LiveDataTestUtil.getValue(viewModel.articleList)

        // 成功扱いか
        assertThat(result?.isSuccess).isTrue()

        // データが存在するか
        assertThat(result?.getOrNull()).isNotNull()

        // データが0件か
        assertThat(result?.getOrNull()).isEmpty()
    }

    @Test
    fun search_API失敗() {
        behavior.apply {
            setDelay(0, TimeUnit.MILLISECONDS) // 即座に結果が返ってくるようにする
            setVariancePercent(0)
            setFailurePercent(0)
            setErrorPercent(100) // APIが必ず失敗するようにする
        }
        viewModel.search(1, 1, "")
        val result = LiveDataTestUtil.getValue(viewModel.articleList)

        // 成功扱いか
        assertThat(result?.isSuccess).isTrue()

        // データが存在するか
        assertThat(result?.getOrNull()).isNotNull()

        // データが0件か
        assertThat(result?.getOrNull()).isEmpty()
    }

    @Test
    fun search_例外() {
        behavior.apply {
            setDelay(0, TimeUnit.MILLISECONDS) // 即座に結果が返ってくるようにする
            setVariancePercent(0)
            setFailurePercent(100) // APIが必ず例外をスローするようにする
            setErrorPercent(0)
        }
        viewModel.search(1, 1, "")
        val result = LiveDataTestUtil.getValue(viewModel.articleList)

        // 失敗扱いか
        assertThat(result?.isFailure).isTrue()

        // 例外が存在するか
        assertThat(result?.exceptionOrNull()).isNotNull()
    }
}
