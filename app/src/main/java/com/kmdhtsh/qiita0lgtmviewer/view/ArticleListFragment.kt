package com.kmdhtsh.qiita0lgtmviewer.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kmdhtsh.qiita0lgtmviewer.R
import com.kmdhtsh.qiita0lgtmviewer.entity.Article
import com.kmdhtsh.qiita0lgtmviewer.entity.User

class ArticleListFragment : Fragment() {

    private lateinit var searchView: SearchView

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_article_list, container, false)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = ArticleRecyclerViewAdapter(createDummyData())
            }
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.options_menu, menu)
        val menuItem = menu.findItem(R.id.search)
        searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(onQueryTextListener)
    }

    private fun createDummyData(): List<Article> {
        return mutableListOf(
            Article(
                "1",
                "aaaa",
                "https://github.com/",
                User(
                    "1",
                    "aaa",
                    "https://qiita-image-store.s3.amazonaws.com/0/93963/profile-images/1518932095"
                )
            ),
            Article(
                "1",
                "aaaa",
                "https://github.com/",
                User(
                    "1",
                    "aaa",
                    "https://qiita-image-store.s3.amazonaws.com/0/93963/profile-images/1518932095"
                )
            ),
            Article(
                "1",
                "aaaa",
                "https://github.com/",
                User(
                    "1",
                    "aaa",
                    "https://qiita-image-store.s3.amazonaws.com/0/93963/profile-images/1518932095"
                )
            ),
            Article(
                "1",
                "aaaa",
                "https://github.com/",
                User(
                    "1",
                    "aaa",
                    "https://qiita-image-store.s3.amazonaws.com/0/93963/profile-images/1518932095"
                )
            ),
            Article(
                "1",
                "aaaa",
                "https://github.com/",
                User(
                    "1",
                    "aaa",
                    "https://qiita-image-store.s3.amazonaws.com/0/93963/profile-images/1518932095"
                )
            ),
            Article(
                "1",
                "aaaa",
                "https://github.com/",
                User(
                    "1",
                    "aaa",
                    "https://qiita-image-store.s3.amazonaws.com/0/93963/profile-images/1518932095"
                )
            ),
            Article(
                "1",
                "aaaa",
                "https://github.com/",
                User(
                    "1",
                    "aaa",
                    "https://qiita-image-store.s3.amazonaws.com/0/93963/profile-images/1518932095"
                )
            ),
            Article(
                "1",
                "aaaa",
                "https://github.com/",
                User(
                    "1",
                    "aaa",
                    "https://qiita-image-store.s3.amazonaws.com/0/93963/profile-images/1518932095"
                )
            ),
            Article(
                "1",
                "aaaa",
                "https://github.com/",
                User(
                    "1",
                    "aaa",
                    "https://qiita-image-store.s3.amazonaws.com/0/93963/profile-images/1518932095"
                )
            ),
            Article(
                "1",
                "aaaa",
                "https://github.com/",
                User(
                    "1",
                    "aaa",
                    "https://qiita-image-store.s3.amazonaws.com/0/93963/profile-images/1518932095"
                )
            ),
            Article(
                "1",
                "aaaa",
                "https://github.com/",
                User(
                    "1",
                    "aaa",
                    "https://qiita-image-store.s3.amazonaws.com/0/93963/profile-images/1518932095"
                )
            ),
        )
    }
}
