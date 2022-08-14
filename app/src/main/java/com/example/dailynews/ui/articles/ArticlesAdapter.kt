package com.example.dailynews.ui.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dailynews.databinding.ItemArticleBinding
import com.example.dailynews.model.network.ArticleResponse

class ArticlesAdapter :
    ListAdapter<ArticleResponse, ArticlesAdapter.ViewHolder>(ArticlesDiffUtilsItemCallback) {
    class ViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                //
            }
        }

        fun bind(article: ArticleResponse) {
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

    object ArticlesDiffUtilsItemCallback : DiffUtil.ItemCallback<ArticleResponse>() {
        override fun areItemsTheSame(oldItem: ArticleResponse, newItem: ArticleResponse): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.author == newItem.author &&
                    oldItem.publishedAt == newItem.publishedAt
        }

        override fun areContentsTheSame(
            oldItem: ArticleResponse,
            newItem: ArticleResponse
        ): Boolean {
            return oldItem == newItem
        }

    }
}