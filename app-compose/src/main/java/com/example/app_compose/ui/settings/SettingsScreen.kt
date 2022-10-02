package com.example.app_compose.ui.settings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    Column {
        Column(
            modifier = modifier
                .combinedClickable(onClick = {}, onLongClick = {})
                .padding(all = 8.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
            ) {
                Text(
                    text = "Total articles:",
                    modifier = modifier
                )
                Text(
                    text = "0",
                    modifier = modifier
                        .padding(start = 4.dp)
                )
            }
            Text(
                text = "Long press to clear",
                modifier = modifier
            )
        }
        Divider()
        Column(
            modifier = modifier
                .combinedClickable(onClick = {}, onLongClick = {})
                .padding(all = 8.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
            ) {
                Text(
                    text = "Cached (not favorite) articles:",
                    modifier = modifier
                )
                Text(
                    text = "0",
                    modifier = modifier
                        .padding(start = 4.dp)
                )
            }
            Text(
                text = "Long press to clear",
                modifier = modifier
            )
        }
        Divider()
        Column(
            modifier = modifier
                .combinedClickable(onClick = {}, onLongClick = {})
                .padding(all = 8.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
            ) {
                Text(
                    text = "Favorite articles:",
                    modifier = modifier
                )
                Text(
                    text = "0",
                    modifier = modifier
                        .padding(start = 4.dp)
                )
            }
            Text(
                text = "Long press to clear",
                modifier = modifier
            )
        }
        Divider()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}