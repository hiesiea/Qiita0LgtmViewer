package com.kmdhtsh.qiita0lgtmviewer.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kmdhtsh.qiita0lgtmviewer.R
import com.kmdhtsh.qiita0lgtmviewer.entity.Article
import com.kmdhtsh.qiita0lgtmviewer.repository.SearchRepository
import com.kmdhtsh.qiita0lgtmviewer.service.SearchService
import com.kmdhtsh.qiita0lgtmviewer.viewmodel.ArticleListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class ArticleListFragment : Fragment() {
    private lateinit var searchView: SearchView
    private lateinit var viewModel: ArticleListViewModel

    private val articleList = mutableListOf<Article>()
    private val articleRecyclerViewAdapter = ArticleRecyclerViewAdapter(articleList)
    private val linearLayoutManager = LinearLayoutManager(context)

    private var previousTotal = 0
    private var loading = true
    private var currentPage = 1
    private var query: String? = null

    private val onQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let {
                clearRecyclerView()
                this@ArticleListFragment.query = it
                viewModel.search(currentPage, PER_PAGE, it)
            }
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }

        private fun clearRecyclerView() {
            previousTotal = 0
            loading = true
            currentPage = 1
            articleList.clear()
        }
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val visibleItemCount = recyclerView.childCount
            val totalItemCount = linearLayoutManager.itemCount
            val firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()
            Log.d(
                TAG,
                "visibleItemCount=$visibleItemCount, totalItemCount=$totalItemCount, firstVisibleItem=$firstVisibleItem"
            )

            if (loading) {
                Log.d(TAG, "load中")
                if (totalItemCount > previousTotal) {
                    Log.d(TAG, "loadおわり")
                    loading = false
                    previousTotal = totalItemCount
                }
            }

            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
                query?.let {
                    Log.d(TAG, "loadする")
                    currentPage++
                    viewModel.search(currentPage, PER_PAGE, it)
                    loading = true
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val searchService = retrofit.create(SearchService::class.java)
        val searchRepository = SearchRepository(searchService)
        viewModel = ArticleListViewModel(searchRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_article_list, container, false)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = linearLayoutManager
                addOnScrollListener(onScrollListener)
                adapter = articleRecyclerViewAdapter
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.articleList.observe(this, { result ->
            result.fold(
                {
                    articleList.addAll(it)
                    articleRecyclerViewAdapter.notifyDataSetChanged()
                },
                {

                }
            )
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.options_menu, menu)
        val menuItem = menu.findItem(R.id.search)
        searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(onQueryTextListener)
    }

    companion object {
        private const val TAG = "ArticleListFragment"
        private const val BASE_URL = "https://qiita.com"
        private const val VISIBLE_THRESHOLD = 5
        private const val PER_PAGE = 20
    }
}
