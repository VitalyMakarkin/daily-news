package com.example.dailynews

import com.example.dailynews.ui.articles.ArticlesFragment
import com.example.dailynews.ui.favorites.FavoriteArticlesFragment
import com.example.dailynews.ui.settings.SettingsFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun toArticles() = FragmentScreen { ArticlesFragment() }
    fun toFavoriteArticles() = FragmentScreen { FavoriteArticlesFragment() }
    fun toSettings() = FragmentScreen { SettingsFragment() }
}