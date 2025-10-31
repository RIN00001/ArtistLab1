package com.example.artistlab.db.container

import com.example.artistlab.db.service.ArtistService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetroInstance {
    private const val BASE_URL = "https://www.theaudiodb.com/api/v1/json/123/"

    val api: ArtistService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArtistService::class.java)
    }
}