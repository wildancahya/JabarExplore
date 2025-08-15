package com.unikom.jabarexplore.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val overpass: OverpassService by lazy {
        Retrofit.Builder()
            .baseUrl("https://overpass-api.de/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OverpassService::class.java)
    }
}
