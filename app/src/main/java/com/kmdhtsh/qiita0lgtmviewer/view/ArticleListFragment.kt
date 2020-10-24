package com.kmdhtsh.qiita0lgtmviewer.view

import android.os.Bundle
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
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ArticleListFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var viewModel: ArticleListViewModel
    private lateinit var articleRecyclerViewAdapter: ArticleRecyclerViewAdapter
    private val articleList = mutableListOf<Article>()


    private val onQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
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
                layoutManager = LinearLayoutManager(context)
                articleRecyclerViewAdapter = ArticleRecyclerViewAdapter(articleList)
                adapter = articleRecyclerViewAdapter
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: スクロールし終わったらさらに次の20件を読み込むようにしたい
        viewModel.search("1", "20", "Java")
        viewModel.articleList.observe(this, { result ->
            result.fold(
                {
                    articleList.clear()
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
    }
}
