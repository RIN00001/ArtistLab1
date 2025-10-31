package com.example.artistlab.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.artistlab.ui.components.TrackList
import com.example.artistlab.ui.components.TrackListItem
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
        title = "Sob Rock",
        year = "2021",
        label = "Indie",
        description = "Sob Rock is the eighth studio album by American singer-songwriter John Mayer.",
        imageUrl = "",
        tracks = listOf(
            Track("1", "Last Train Home", "3:07", 1),
            Track("2", "Shouldn't Matter but It Does", "3:56", 2),
            Track("3", "New Light", "3:37", 3)
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
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
                .padding(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Box(Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(displayAlbum.imageUrl.takeIf { it.isNotBlank() })
                                .crossfade(true)
                                .build(),
                            contentDescription = "Album cover for ${displayAlbum.title}",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = displayAlbum.title,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                Row {
                    Text("Year: ${displayAlbum.year}", color = Color.Gray, fontSize = 14.sp)
                    Text(" • ", color = Color.Gray, fontSize = 14.sp)
                    Text("Label: ${displayAlbum.label}", color = Color.Gray, fontSize = 14.sp)
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = displayAlbum.description,
                    color = Color.White,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )

                Spacer(Modifier.height(24.dp))

                Text(
                    text = "Tracks",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                if (displayAlbum.tracks.isNotEmpty()) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        displayAlbum.tracks.forEachIndexed { index, track ->
                            TrackListItem(track = track, trackNumber = index + 1)
                        }
                    }
                } else {
                    Text(
                        text = "No tracks available",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

