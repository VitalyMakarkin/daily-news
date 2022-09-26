package com.example.dailynews.ui.favorites

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.shared.presentation.FavoriteArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteArticlesFragment : Fragment() {
    private val viewModel: FavoriteArticlesViewModel by viewModels()
}