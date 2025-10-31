package com.example.artistlab.db.service

import com.example.artistlab.db.dto.AlbumResponse
import com.example.artistlab.db.dto.ArtistResponse
import com.example.artistlab.db.dto.TrackResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistService{
    @GET("search.php")
    suspend fun getArtists(
        @Query("s") artistName: String
    ): ArtistResponse

    @GET("searchalbum.php")
    suspend fun getAlbums(
        @Query("s") artistName: String
    ): AlbumResponse

    @GET("track.php")
    suspend fun getTracks(
        @Query("m") albumId: String
    ): TrackResponse
}