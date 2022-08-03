package com.example.dailynews.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dailynews.R
import com.example.dailynews.databinding.ActivityMainBinding
import com.example.dailynews.ui.articles.ArticlesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportFragmentManager.fragments.isEmpty()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ArticlesFragment())
                .commit()
        }
    }
}