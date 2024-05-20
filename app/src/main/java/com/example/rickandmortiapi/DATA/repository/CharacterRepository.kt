package com.example.rickandmortiapi.DATA.repository

import com.example.rickandmortiapi.model.CharacterList
import com.example.rickandmortiapi.DI.Retrofit.RetrofitInstance
import retrofit2.Response
import javax.inject.Inject

class CharacterRepository @Inject constructor() {

    suspend fun getCharacters(page: Int): Response<CharacterList> {
        return RetrofitInstance.api.getCharacters(page)
    }

    suspend fun getCharactersByStatusAndGender(status: String, gender: String, page: Int): Response<CharacterList> {
        return RetrofitInstance.api.getCharactersByStatusAndGender(page, status, gender)
    }

    suspend fun getCharactersByStatus(page: Int, status: String): Response<CharacterList> {
        return RetrofitInstance.api.getCharactersByStatus(page, status)
    }

    suspend fun getCharactersByGender(gender: String, page: Int): Response<CharacterList> {
        return RetrofitInstance.api.getCharactersByGender(page, gender)
    }

    suspend fun getCharactersByName(name: String): Response<CharacterList> {
        return RetrofitInstance.api.getCharactersByName(name)
    }
}
