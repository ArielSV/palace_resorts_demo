package com.example.palace_resorts.flows.views

import android.view.View
import com.example.palace_resorts.R
import com.example.palace_resorts.databinding.NewsItemBinding
import com.example.palace_resorts.flows.models.Articles
import com.example.palace_resorts.utils.imageUtils.loadImages
import com.xwray.groupie.viewbinding.BindableItem

class NewsItemView(
    private val article: Articles?,
    private val position: Int,
    private val listener: (Articles) -> Unit,
) : BindableItem<NewsItemBinding>() {

    override fun getLayout() = R.layout.news_item

    override fun bind(binding: NewsItemBinding, position: Int) {
        binding.apply {
            val index = position + 1
            textViewName.text = article?.author
            textViewDescription.text = article?.content
            imageviewIcon.text = index.toString()
            textViewDate.text = article?.publishedAt
            loadImages(binding.root.context, article?.urlToImage.orEmpty(), imageViewNew)
        }
    }

    override fun initializeViewBinding(view: View) = NewsItemBinding.bind(view)
}
