package com.example.dailynews.ui.shared

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dailynews.databinding.ItemImageArticleBinding
import com.example.dailynews.databinding.ItemTextArticleBinding
import com.example.shared.presentation.model.BaseArticleUI
import com.example.shared.presentation.model.ImageArticleUI
import com.example.shared.presentation.model.TextArticleUI

const val TYPE_TEXT = 0
const val TYPE_IMAGE = 1

class ArticlesAdapter(private val itemHandler: ItemHandler) :
    ListAdapter<BaseArticleUI, RecyclerView.ViewHolder>(ArticlesDiffUtilsItemCallback) {

    interface ItemHandler {
        fun onItemClicked(articleId: Int)
        fun onFavoriteItemMarked(articleId: Int)
        fun onFavoriteItemUnmarked(articleId: Int)
    }

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
            is TextViewHolder -> holder.bind(getItem(position) as TextArticleUI, itemHandler)
            is ImageViewHolder -> holder.bind(getItem(position) as ImageArticleUI, itemHandler)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is TextArticleUI -> TYPE_TEXT
            is ImageArticleUI -> TYPE_IMAGE
            else -> throw Exception("View type not implemented")
        }
    }

    object ArticlesDiffUtilsItemCallback : DiffUtil.ItemCallback<BaseArticleUI>() {
        override fun areItemsTheSame(oldItem: BaseArticleUI, newItem: BaseArticleUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BaseArticleUI, newItem: BaseArticleUI): Boolean {
            return oldItem == newItem
        }
    }

    class TextViewHolder(private val binding: ItemTextArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: TextArticleUI, itemHandler: ItemHandler) {
            binding.titleTv.text = article.title
            binding.descriptionTv.text = article.description
            binding.publishedAtTv.text = article.publishedAt

            binding.favoriteMarkedIv.visibility = when (article.isFavorite) {
                true -> View.VISIBLE
                else -> View.GONE
            }

            binding.favoriteUnmarkedIv.visibility = when (article.isFavorite) {
                true -> View.GONE
                else -> View.VISIBLE
            }

            itemView.setOnClickListener {
                itemHandler.onItemClicked(article.id)
            }

            binding.favoriteMarkedIv.setOnClickListener {
                itemHandler.onFavoriteItemUnmarked(article.id)
            }

            binding.favoriteUnmarkedIv.setOnClickListener {
                itemHandler.onFavoriteItemMarked(article.id)
            }
        }
    }

    class ImageViewHolder(private val binding: ItemImageArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: ImageArticleUI, itemHandler: ItemHandler) {
            binding.titleTv.text = article.title
            binding.publishedAtTv.text = article.publishedAt
            binding.imageIv.load(article.urlToImage) {
                placeholder(ColorDrawable(0xEEEEEE))
                crossfade(true)
            }

            binding.favoriteMarkedIv.visibility = when (article.isFavorite) {
                true -> View.VISIBLE
                else -> View.GONE
            }

            binding.favoriteUnmarkedIv.visibility = when (article.isFavorite) {
                true -> View.GONE
                else -> View.VISIBLE
            }

            itemView.setOnClickListener {
                itemHandler.onItemClicked(article.id)
            }

            binding.favoriteMarkedIv.setOnClickListener {
                itemHandler.onFavoriteItemUnmarked(article.id)
            }

            binding.favoriteUnmarkedIv.setOnClickListener {
                itemHandler.onFavoriteItemMarked(article.id)
            }
        }
    }
}