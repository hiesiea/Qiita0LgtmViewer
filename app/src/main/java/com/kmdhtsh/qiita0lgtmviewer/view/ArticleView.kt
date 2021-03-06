package com.kmdhtsh.qiita0lgtmviewer.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.kmdhtsh.qiita0lgtmviewer.R
import com.kmdhtsh.qiita0lgtmviewer.databinding.ViewArticleBinding
import com.kmdhtsh.qiita0lgtmviewer.entity.Article

/**
 * 記事表示用View
 */
class ArticleView : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val binding: ViewArticleBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_article, this, true)

    /**
     * 記事をViewに設定
     * @param article 記事
     */
    fun setArticle(article: Article) {
        binding.article = article
    }
}
