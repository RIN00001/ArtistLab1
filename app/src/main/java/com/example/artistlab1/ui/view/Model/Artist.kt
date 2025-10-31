package com.example.artistlab.ui.model

data class Artist(
    val name: String,
    val year: String,
    val label: String,
    val imageUrl: String,
    val albums: List<Album>
)

data class Track(
    val id: String = "",
    val title: String = "Unknown",
    val duration: String = "-",
    val trackNumber: Int = 0
)

data class Album(
    val id: String = "",
    val title: String = "Unknown Album",
    val year: String = "-",
    val label: String = "-",
    val description: String = "",
    val imageUrl: String = "",
    val tracks: List<Track> = emptyList()
)
