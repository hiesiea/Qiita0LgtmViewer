package com.kmdhtsh.qiita0lgtmviewer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kmdhtsh.qiita0lgtmviewer.R
import com.kmdhtsh.qiita0lgtmviewer.entity.Article
import com.kmdhtsh.qiita0lgtmviewer.entity.User

class ArticleListFragment : Fragment() {

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

    companion object {

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() =
            ArticleListFragment()
    }
}