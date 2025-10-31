package com.example.artistlab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.artistlab1.ui.theme.ArtistLab1Theme
import com.example.artistlab1.ui.view.ArtistLabApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtistLab1Theme {
                    ArtistLabApp()
            }
        }
    }
}

