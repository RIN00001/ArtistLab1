package com.example.artistlab.db.dto

import com.google.gson.annotations.SerializedName

data class TrackResponse(
    @SerializedName("track")
    val tracks: List<TrackDto>?
)

data class TrackDto(
    @SerializedName("idTrack")
    val id: String?,
    @SerializedName("strTrack")
    val title: String?,
    @SerializedName("intDuration")
    val duration: String?,
    @SerializedName("intTrackNumber")
    val trackNumber: String?
)