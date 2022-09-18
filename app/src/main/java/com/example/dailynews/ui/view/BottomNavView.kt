package com.example.dailynews.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.dailynews.databinding.ViewBottomNavBinding
import timber.log.Timber

class BottomNavView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    private val binding = ViewBottomNavBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.articlesButton.setOnClickListener {
            Timber.d("articlesButton clicked")
            // TODO: Pass click event through interface
        }

        binding.favoriteArticlesButton.setOnClickListener {
            Timber.d("favoriteArticlesButton clicked")
            // TODO: Pass click event through interface
        }

        binding.settingsButton.setOnClickListener {
            Timber.d("settingsButton clicked")
            // TODO: Pass click event through interface
        }
    }

    // TODO: Add buttons state (active/inactive state with color)
}