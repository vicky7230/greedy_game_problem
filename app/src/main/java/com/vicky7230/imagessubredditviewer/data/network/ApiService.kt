package com.vicky7230.imagessubredditviewer.data.network

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("r/images/hot.json")
    suspend fun getImages(): Response<JsonElement>
}