package com.example.dailynews.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.dailynews.databinding.ViewBottomNavButtonBinding

class BottomNavButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    val binding = ViewBottomNavButtonBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    // TODO: Add icon color switcher
}