package com.example.artistlab.db.dto

import com.google.gson.annotations.SerializedName

data class ArtistResponse(
    @SerializedName("artists")
    val artists: List<ArtistDto>?
)

data class ArtistDto(
    @SerializedName("strArtist")
    val name: String?,
    @SerializedName("intFormedYear")
    val year: String?,
    @SerializedName("strLabel")
    val label: String?,
    @SerializedName("strArtistThumb")
    val imageUrl: String?,
)