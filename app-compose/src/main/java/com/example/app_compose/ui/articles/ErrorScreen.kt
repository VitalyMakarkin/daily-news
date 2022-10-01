package com.example.app_compose.ui.articles

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorScreen(
    message: String,
    modifier: Modifier = Modifier,
    refresh: () -> Unit
) {
    // TODO: Implement
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(message = "Error!") {
        // TODO: Implement action
    }
}