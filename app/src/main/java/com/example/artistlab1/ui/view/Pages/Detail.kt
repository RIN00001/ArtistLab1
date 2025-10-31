package com.example.artistlab.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artistlab.ui.components.AlbumDetailCard
import com.example.artistlab.ui.components.TrackList
import com.example.artistlab.ui.model.Album
import com.example.artistlab.ui.model.Track

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPage(
    album: Album? = null,
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val displayAlbum = album ?: Album(
        id = "1",
        title = "Continuum",
        year = "2006",
        label = "Columbia",
        description = "A blues-pop album by John Mayer, featuring soulful melodies and emotional lyricism.",
        imageUrl = "https://www.theaudiodb.com/images/media/album/thumb/xxxxx.jpg",
        tracks = listOf(
            Track("1", "Gravity", "4:06", 1),
            Track("2", "Slow Dancing in a Burning Room", "4:11", 2),
            Track("3", "Vultures", "3:58", 3)
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF2A2A2A))
    ) {
        TopAppBar(
            title = {
                Text(
                    text = displayAlbum.title,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF1C1C1C)
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            item {
                AlbumDetailCard(
                    album = displayAlbum,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Text(
                    text = "Tracks",
                    color = Color(0xFFBCA13A),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                if (displayAlbum.tracks.isNotEmpty()) {
                    TrackList(tracks = displayAlbum.tracks)
                } else {
                    Text(
                        text = "No tracks available",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailPagePreview() {
    val sampleTracks = listOf(
        Track("1", "Gravity", "4:06", 1),
        Track("2", "Slow Dancing in a Burning Room", "4:11", 2),
        Track("3", "Vultures", "3:58", 3)
    )
    val sampleAlbum = Album(
        id = "1",
        title = "Continuum",
        year = "2006",
        label = "Columbia",
        description = "A blues-pop album by John Mayer.",
        imageUrl = "https://www.theaudiodb.com/images/media/album/thumb/xxxxx.jpg",
        tracks = sampleTracks
    )

    MaterialTheme {
        DetailPage(album = sampleAlbum)
    }
}
