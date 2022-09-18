package com.example.dailynews.ui.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dailynews.databinding.ItemArticleBinding
import com.example.shared.presentation.model.ArticleUI

class ArticlesAdapter :
    ListAdapter<ArticleUI, ArticlesAdapter.ViewHolder>(ArticlesDiffUtilsItemCallback) {
    class ViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                // TODO("Implement and bind onItemClick to display article by using navigation")
            }
        }

        fun bind(article: ArticleUI) {
            binding.titleTv.text = article.title
            binding.publishedAtTv.text = article.publishedAt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
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
}