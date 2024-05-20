package com.example.rickandmortiapi.DATA.api

import com.example.rickandmortiapi.model.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {
    @GET("api/character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharacterList>

    @GET("api/character")
    suspend fun getCharactersByStatusAndGender(
        @Query("page") page: Int,
        @Query("status") status: String,
        @Query("gender") gender: String,

        ): Response<CharacterList>

    @GET("api/character")
    suspend fun getCharactersByStatus(
        @Query("page") page: Int,
        @Query("status") status: String
    ): Response<CharacterList>

    @GET("api/character")
    suspend fun getCharactersByGender(
        @Query("page") page: Int,
        @Query("gender") gender: String
    ): Response<CharacterList>


    @GET("api/character")
    suspend fun getCharactersByName(
        @Query("name") name: String): Response<CharacterList>
}