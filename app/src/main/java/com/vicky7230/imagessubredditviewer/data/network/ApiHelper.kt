package com.vicky7230.imagessubredditviewer.data.network

import com.google.gson.JsonElement
import retrofit2.Response


interface ApiHelper {
    suspend fun getImages(): Response<JsonElement>
}