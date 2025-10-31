package com.example.artistlab.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.artistlab.ui.components.ArtistBannerCard
import com.example.artistlab.ui.components.TopBanner
import com.example.artistlab.ui.components.AlbumCard
import com.example.artistlab.ui.model.ArtistViewModel
import com.example.artistlab.ui.model.ArtistUIState
import com.example.artistlab.ui.model.Album

@Composable
fun HomePage(
    viewModel: ArtistViewModel = viewModel(),
    onAlbumClick: (Album) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val artistState by viewModel.artistState.collectAsState()

    when (val state = artistState) {
        is ArtistUIState.Loading -> {

            LoadingPage()
        }

        is ArtistUIState.Error -> {
            ErrorPage(errorMessage = state.message)
        }

        is ArtistUIState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = Color(0xFF2A2A2A))
            ) {
                TopBanner(title = state.artist.name)

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    item {
                        ArtistBannerCard(
                            viewModel = viewModel,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    if (state.artist.albums.isNotEmpty()) {
                        item {
                            Column {
                                Text(
                                    text = "Albums",
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )

                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(2),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier.height(
                                        (((state.artist.albums.size / 2) +
                                                (state.artist.albums.size % 2)) * 230).dp
                                    )
                                ) {
                                    items(state.artist.albums) { album ->
                                        AlbumCard(
                                            album = album,
                                            onAlbumClick = onAlbumClick
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        item {
                            Text(
                                text = "No albums available",
                                color = Color.Gray,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }

                    item { Spacer(modifier = Modifier.height(16.dp)) }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePagePreview() {
    MaterialTheme {
        HomePage()
    }
}
