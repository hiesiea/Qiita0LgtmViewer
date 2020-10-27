package com.kmdhtsh.qiita0lgtmviewer.view

import android.net.Uri
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.kmdhtsh.qiita0lgtmviewer.entity.Article
import timber.log.Timber

/**
 * 記事表示用RecyclerViewAdapter
 */
class ArticleRecyclerViewAdapter(
    private val values: List<Article>
) : RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ArticleView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.articleView.setArticle(item)
        holder.articleView.setOnClickListener {
            Timber.d("onClick url=${item.url}")
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(holder.articleView.context, Uri.parse(item.url))
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: ArticleView) : RecyclerView.ViewHolder(view) {
        val articleView = view
    }
}
