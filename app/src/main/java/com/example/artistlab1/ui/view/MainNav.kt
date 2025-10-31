package com.example.artistlab1.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.artistlab.ui.components.TopBanner
import com.example.artistlab.ui.model.Album
import com.example.artistlab.ui.model.ArtistViewModel
import com.example.artistlab.ui.pages.DetailPage
import com.example.artistlab.ui.pages.HomePage

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArtistLabApp() {
    val navController = rememberNavController()
    val viewModel: ArtistViewModel = viewModel()

    var selectedAlbum by remember { mutableStateOf<Album?>(null) }

    Scaffold(
        modifier = Modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomePage(
                    viewModel = viewModel,
                    onAlbumClick = { album ->
                        selectedAlbum = album
                        navController.navigate("detail")
                    }
                )
            }


            composable("detail") {
                DetailPage(
                    album = selectedAlbum,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}



