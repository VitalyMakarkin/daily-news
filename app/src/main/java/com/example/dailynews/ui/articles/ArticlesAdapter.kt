package com.example.dailynews.ui.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dailynews.databinding.ItemArticleBinding
import com.example.dailynews.model.Article

class ArticlesAdapter :
    ListAdapter<Article, ArticlesAdapter.ViewHolder>(ArticlesDiffUtilsItemCallback) {
    class ViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.titleTv.text = article.title
            binding.publishedAtTv.text = article.publishedAt
            // TODO("Implement and bind onItemClick to display article by using navigation")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object ArticlesDiffUtilsItemCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.author == newItem.author &&
                    oldItem.publishedAt == newItem.publishedAt
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
}