package com.example.artistlab.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.artistlab.ui.model.Album
import com.example.artistlab.ui.model.ArtistViewModel
import com.example.artistlab.ui.model.Artist
import com.example.artistlab.ui.model.ArtistUIState

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlbumDetailPreviewSample() {
    val sampleAlbum = Album(
        id = "1",
        title = "Continuum",
        year = "2006",
        label = "Indie",
        description = "Sample description for preview.",
        imageUrl = "https://www.theaudiodb.com/images/media/album/thumb/xxxxx.jpg",
        tracks = listOf()
    )
    val sampleArtist = Artist(
        name = "John Mayer",
        year = "1998",
        label = "Indie",
        imageUrl = "",
        albums = listOf(sampleAlbum)
    )

    AlbumDetailContent(artist = sampleArtist)
}


@Composable
fun AlbumDetailCard(
    viewModel: ArtistViewModel = viewModel(),
    modifier: Modifier = Modifier
){
    val artistsState by viewModel.artistState.collectAsState()
        when (val currentState = artistsState){
            is ArtistUIState.Loading -> {
                LoadingAlbumCard()
            }
            is ArtistUIState.Success ->{
                AlbumDetailContent(currentState.artist)
            }
            is ArtistUIState.Error -> {
                ErrorAlbumCard(
                    errorMessage = currentState.message,
                    onRetry = { viewModel.retryFetch() }
                )
            }
        }


}


@Composable
fun AlbumDetailContent(
    artist: Artist,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(26.dp)
            .border(
                width = 3.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF1E2223))
    ) {
        if (artist.albums.isNotEmpty()) {
            val firstAlbum = artist.albums.first()
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
                            .data(firstAlbum.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Album cover for ${firstAlbum.title}",
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
                    text = firstAlbum.title,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(12.dp))
                Row {
                    Text(text = " ${firstAlbum.year}", color = Color.Gray, fontSize = 14.sp)
                    Spacer(Modifier.width(8.dp))
                    Text(text = "•", color = Color.Gray, fontSize = 14.sp)
                    Spacer(Modifier.width(8.dp))
                    Text(text = "${firstAlbum.label}", color = Color.Gray, fontSize = 14.sp)
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    text = firstAlbum.description.ifBlank { "No description available." },
                    color = Color.White,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        } else {
            Text(
                text = "No albums available",
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = modifier.padding(16.dp)
            )
        }
    }
}


@Composable
fun LoadingAlbumCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(340.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.3f))
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = Color(0xFFB8860B)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Loading artist data...",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun ErrorAlbumCard(
    errorMessage: String,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(340.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.1f))
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Error loading artist",
                    color = Color.Red,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = errorMessage,
                    color = Color.Red.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onRetry,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB8860B)
                    )
                ) {
                    Text(
                        text = "Retry",
                        color = Color.White
                    )
                }
            }
        }
    }
}
