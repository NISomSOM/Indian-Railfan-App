package com.example.indianrailfanapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// 1. The baseUrl should only be the main domain.
private val retrofit = Retrofit.Builder().baseUrl("https://gist.githubusercontent.com/NISomSOM/4a5d481f65caff5b5c156f6cf87ee523/raw/592c46890180ed4b5a9bca8c5eae0697cbe1088d/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val locoService: ApiService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("locomotives.json")
    suspend fun getLocos(): LocoResponse
}