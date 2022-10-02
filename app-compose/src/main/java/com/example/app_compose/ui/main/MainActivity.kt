package com.example.app_compose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.app_compose.ui.articles.ArticlesScreen
import com.example.app_compose.ui.favorites.FavoriteArticlesScreen
import com.example.app_compose.ui.settings.SettingsScreen
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DailyNews(
    navHostController: NavHostController = rememberNavController()
) {
    Scaffold(bottomBar = {
        BottomNavigation(
            backgroundColor = Color.White
        ) {
            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = com.example.shared.R.drawable.ic_articles_list),
                        contentDescription = null
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == "articles" } == true,
                onClick = { navHostController.navigate("articles") }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = com.example.shared.R.drawable.ic_favorite_articles_list),
                        contentDescription = null
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == "favorite_articles" } == true,
                onClick = { navHostController.navigate("favorite_articles") }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = com.example.shared.R.drawable.ic_settings),
                        contentDescription = null
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == "settings" } == true,
                onClick = { navHostController.navigate("settings") })
        }
    }) {
        NavHost(
            navController = navHostController,
            startDestination = "articles",
            modifier = Modifier.padding(it)
        ) {
            composable("articles") {
                ArticlesScreen()
            }
            composable("favorite_articles") {
                FavoriteArticlesScreen()
            }
            composable("settings") {
                SettingsScreen()
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