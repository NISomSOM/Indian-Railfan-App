package com.example.indianrailfanapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().baseUrl("https://gist.githubusercontent.com/NISomSOM/4a5d481f65caff5b5c156f6cf87ee523/raw/2d45a85e4830599bd434aeb3fdeefd94d99f0377/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val locoService: ApiService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("locomotives.json")
    suspend fun getLocos(): LocoResponse
}