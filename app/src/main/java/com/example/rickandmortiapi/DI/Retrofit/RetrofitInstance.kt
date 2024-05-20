package com.example.rickandmortiapi.DI.Retrofit

import com.example.rickandmortiapi.DATA.api.SimpleApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}