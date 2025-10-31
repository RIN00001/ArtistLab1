package com.example.artistlab.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBanner(
    title: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFF1E2223),
    textColor: Color = Color(0xFFFFFFFF)
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = textColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            letterSpacing = 0.5.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBannerPreview() {
    Column {
        TopBanner(title = "John Mayer")
        Spacer(modifier = Modifier.height(8.dp))
        TopBanner(title = "Sob Rock")
        Spacer(modifier = Modifier.height(8.dp))
        TopBanner(title = "Loading")
        Spacer(modifier = Modifier.height(8.dp))
        TopBanner(title = "Error",)
    }
}