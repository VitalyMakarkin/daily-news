package com.example.dailynews

import com.example.dailynews.ui.article.ArticleFragment
import com.example.dailynews.ui.articles.ArticlesFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun Articles() = FragmentScreen {
        ArticlesFragment()
    }

    fun Article(article: String) = FragmentScreen {
        ArticleFragment.newInstance(article)
    }
}