package com.example.dailynews.ui.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dailynews.databinding.ItemImageArticleBinding
import com.example.dailynews.databinding.ItemTextArticleBinding
import com.example.shared.presentation.model.ArticleUI
import com.example.shared.presentation.model.ImageArticleUI
import com.example.shared.presentation.model.TextArticleUI

const val TYPE_TEXT = 0
const val TYPE_IMAGE = 1

class ArticlesAdapter :
    ListAdapter<ArticleUI, RecyclerView.ViewHolder>(ArticlesDiffUtilsItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_TEXT -> TextViewHolder(ItemTextArticleBinding.inflate(inflater, parent, false))
            TYPE_IMAGE -> ImageViewHolder(ItemImageArticleBinding.inflate(inflater, parent, false))
            else -> throw Exception("Not found view holder")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TextViewHolder -> holder.bind(getItem(position) as TextArticleUI)
            is ImageViewHolder -> holder.bind(getItem(position) as ImageArticleUI)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is TextArticleUI -> TYPE_TEXT
            is ImageArticleUI -> TYPE_IMAGE
        }
    }

    object ArticlesDiffUtilsItemCallback : DiffUtil.ItemCallback<ArticleUI>() {
        override fun areItemsTheSame(oldItem: ArticleUI, newItem: ArticleUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ArticleUI,
            newItem: ArticleUI
        ): Boolean {
            return oldItem == newItem
        }

    }

    class TextViewHolder(private val binding: ItemTextArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                // TODO("Implement and bind onItemClick to display article by using navigation")
            }
        }

        fun bind(article: TextArticleUI) {
            binding.titleTv.text = article.title
            binding.publishedAtTv.text = article.publishedAt
        }
    }

    class ImageViewHolder(private val binding: ItemImageArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                // TODO("Implement and bind onItemClick to display article by using navigation")
            }
        }

        fun bind(article: ImageArticleUI) {
            binding.titleTv.text = article.title
            binding.publishedAtTv.text = article.publishedAt
            binding.imageIv.setImageURI(article.urlToImage.toUri())
        }
    }
}