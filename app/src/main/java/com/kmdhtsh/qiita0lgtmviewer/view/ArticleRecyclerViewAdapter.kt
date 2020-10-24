package com.kmdhtsh.qiita0lgtmviewer.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kmdhtsh.qiita0lgtmviewer.entity.Article

class ArticleRecyclerViewAdapter(
    private val values: List<Article>
) : RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ArticleView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.articleView.setArticle(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: ArticleView) : RecyclerView.ViewHolder(view) {
        val articleView = view
    }
}
