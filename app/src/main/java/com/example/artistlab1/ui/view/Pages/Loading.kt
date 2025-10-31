package com.example.artistlab.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artistlab.ui.components.TopBanner

@Composable
fun LoadingPage(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
            .background(color = Color(0xFF2A2A2A))
    ) {
        TopBanner(title = "Loading")

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
,
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Color(0xFFB8860B),
                strokeWidth = 3.dp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPagePreview() {
    MaterialTheme {
        LoadingPage()
    }
}
