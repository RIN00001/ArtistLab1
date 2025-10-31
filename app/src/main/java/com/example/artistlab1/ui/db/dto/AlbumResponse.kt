package com.example.artistlab.db.dto

import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    @SerializedName("album")
    val albums: List<AlbumDto>?
)

data class AlbumDto(
    @SerializedName("idAlbum")
    val id: String?,
    @SerializedName("strAlbum")
    val title: String?,
    @SerializedName("intYearReleased")
    val year: String?,
    @SerializedName("strAlbumThumb")
    val imageUrl: String?,
    @SerializedName("strLabel")
    val label: String?,
    @SerializedName("strDescriptionEN")
    val description: String?
)
