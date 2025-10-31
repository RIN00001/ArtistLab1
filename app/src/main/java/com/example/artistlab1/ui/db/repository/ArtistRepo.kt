package com.example.artistlab.db.repository

import com.example.artistlab.db.container.RetroInstance
import com.example.artistlab.ui.model.Artist
import com.example.artistlab.ui.model.Album
import com.example.artistlab.ui.model.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArtistRepo {

    private val artistName = "Michael_Jackson"

    suspend fun getArtistData(): Artist? = withContext(Dispatchers.IO) {
        try {
            val artistResponse = RetroInstance.api.getArtists(artistName)
            val artistDto = artistResponse.artists?.firstOrNull()
                ?: return@withContext null

            val albumResponse = RetroInstance.api.getAlbums(artistName)
            val albums = albumResponse.albums?.mapNotNull { albumDto ->
                val trackResponse = albumDto.id?.let {
                    RetroInstance.api.getTracks(it)
                }

                val tracks = trackResponse?.tracks?.mapIndexedNotNull { index, trackDto ->
                    Track(
                        id = trackDto.id ?: "unknown_$index",
                        title = trackDto.title ?: "Unknown Title",
                        duration = formatDuration(trackDto.duration),
                        trackNumber = trackDto.trackNumber?.toIntOrNull() ?: (index + 1)
                    )
                }?.sortedBy { it.trackNumber } ?: emptyList()

                Album(
                    id = albumDto.id ?: "unknown_album",
                    title = albumDto.title ?: "Unknown Album",
                    year = albumDto.year ?: "Unknown Year",
                    label = albumDto.label ?: "Unknown Label",
                    description = albumDto.description ?: "No description available",
                    imageUrl = processImageUrl(albumDto.imageUrl),
                    tracks = tracks
                )
            } ?: emptyList()

            Artist(
                name = artistDto.name ?: "Unknown Artist",
                year = artistDto.year ?: "-",
                label = artistDto.label ?: "-",
                imageUrl = processImageUrl(artistDto.imageUrl),
                albums = albums
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun formatDuration(durationMs: String?): String {
        if (durationMs.isNullOrBlank()) return "-"
        return try {
            val totalSeconds = durationMs.toInt() / 1000
            val minutes = totalSeconds / 60
            val seconds = totalSeconds % 60
            "%d:%02d".format(minutes, seconds)
        } catch (e: Exception) {
            "-"
        }
    }

    private fun processImageUrl(imageUrl: String?): String {
        if (imageUrl.isNullOrBlank()) return ""
        var processedUrl = imageUrl.trim()

        // Optional:
        // if (processedUrl.startsWith("http://")) {
        //     processedUrl = processedUrl.replace("http://", "https://")
        // }

        return processedUrl
    }
}
