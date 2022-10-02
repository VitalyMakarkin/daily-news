package com.example.app_compose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
fun DailyNews(
    navHostController: NavHostController = rememberNavController()
) {
    Scaffold(bottomBar = {
        BottomNavigation() {
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = com.example.app_compose.R.drawable.ic_articles_list),
                        contentDescription = null
                    )
                },
                selected = false,
                onClick = { /*TODO*/ })
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = com.example.app_compose.R.drawable.ic_favorite_articles_list),
                        contentDescription = null
                    )
                },
                selected = false,
                onClick = { /*TODO*/ })
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = com.example.app_compose.R.drawable.ic_settings),
                        contentDescription = null
                    )
                },
                selected = false,
                onClick = { /*TODO*/ })
        }
    }) {
        NavHost(
            navController = navHostController,
            startDestination = "articles",
        ) {
            composable("articles") {
                ArticlesScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DailyNewsPreview() {
    DailynewsTheme {
        DailyNews()
    }
}