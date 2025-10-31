package com.example.artistlab.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.artistlab.ui.model.*

@Composable
fun AlbumCard(
    album: Album,
    modifier: Modifier = Modifier,
    onAlbumClick: (Album) -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(160.dp)
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        shape = RoundedCornerShape(12.dp),
        onClick = { onAlbumClick(album) }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            val request = ImageRequest.Builder(LocalContext.current)
                .data(album.imageUrl.takeIf { it.isNotBlank() })
                .crossfade(true)
                .allowHardware(false)
                .build()

            AsyncImage(
                model = request,
                contentDescription = "Album cover for ${album.title}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(144.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = album.title,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "${album.year} • ${album.label}",
                color = Color.Gray,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun AlbumCardPreviewSample() {
    val sampleAlbum = Album(
        id = "1",
        title = "Continuum",
        year = "2006",
        label = "Columbia",
        description = "Sample album description",
        imageUrl = "https://www.theaudiodb.com/images/media/album/thumb/xxxxx.jpg",
        tracks = emptyList()
    )

    Box(
        modifier = Modifier
            .width(160.dp)
            .height(230.dp)
            .background(Color(0xFF121212))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        AlbumCard(album = sampleAlbum)
    }
}