package com.example.app_compose.ui.articles

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
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