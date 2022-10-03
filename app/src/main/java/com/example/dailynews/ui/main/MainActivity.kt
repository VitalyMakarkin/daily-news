package com.example.dailynews.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.dailynews.R
import com.example.dailynews.Screens.toArticles
import com.example.dailynews.Screens.toFavoriteArticles
import com.example.dailynews.Screens.toSettings
import com.example.dailynews.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    lateinit var binding: ActivityMainBinding

    private val navigator: Navigator = object : AppNavigator(this, R.id.container) {
        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
            supportFragmentManager.addOnBackStackChangedListener {
                binding.bottomNav.visibility = when (supportFragmentManager.backStackEntryCount) {
                    0 -> View.VISIBLE
                    else -> View.GONE
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(toArticles())))
        }
    }

    private fun initViews() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.articles_item -> {
                    navigator.applyCommands(arrayOf<Command>(Replace(toArticles())))
                }
                R.id.favorite_articles_item -> {
                    navigator.applyCommands(arrayOf<Command>(Replace(toFavoriteArticles())))
                }
                R.id.settings_item -> {
                    navigator.applyCommands(arrayOf<Command>(Replace(toSettings())))
                }
            }

            return@setOnItemSelectedListener true
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}