package com.example.app_compose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app_compose.ui.articles.ArticlesScreen
import com.example.app_compose.ui.theme.DailynewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailynewsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DailyNews()
                }
            }
        }
    }
}

@Composable
fun DailyNews() {
    // TODO: Implement navigation
    ArticlesScreen()
}

@Preview(showBackground = true)
@Composable
fun DailyNewsPreview() {
    DailynewsTheme {
        DailyNews()
    }
}