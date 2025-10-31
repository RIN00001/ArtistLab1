package com.example.artistlab.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.artistlab.ui.model.Album

@Composable
fun AlbumDetailCard(
    album: Album,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .border(
                width = 2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF1E2223))
    ) {
        val cardModifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .clip(RoundedCornerShape(16.dp))

        Card(
            modifier = cardModifier,
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(album.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Album cover for ${album.title}",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Column(Modifier.padding(12.dp)) {
            Spacer(Modifier.height(16.dp))
            Text(
                text = album.title,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(12.dp))
            Row {
                Text(text = album.year, color = Color.Gray, fontSize = 14.sp)
                Spacer(Modifier.width(8.dp))
                Text(text = "•", color = Color.Gray, fontSize = 14.sp)
                Spacer(Modifier.width(8.dp))
                Text(text = album.label, color = Color.Gray, fontSize = 14.sp)
            }
            Spacer(Modifier.height(16.dp))
            Text(
                text = album.description.ifBlank { "No description available." },
                color = Color.White,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlbumDetailCardPreview() {
    val sampleAlbum = Album(
        id = "1",
        title = "Continuum",
        year = "2006",
        label = "Columbia",
        description = "A blues-pop album by John Mayer, featuring soulful melodies and emotional lyricism.",
        imageUrl = "https://www.theaudiodb.com/images/media/album/thumb/xxxxx.jpg",
        tracks = emptyList()
    )

    AlbumDetailCard(album = sampleAlbum)
}
