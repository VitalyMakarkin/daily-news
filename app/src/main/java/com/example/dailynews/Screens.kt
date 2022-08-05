package com.example.dailynews

import com.example.dailynews.ui.articles.ArticlesFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun Articles() = FragmentScreen {
        ArticlesFragment()
    }
}