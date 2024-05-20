package com.example.rickandmortiapi.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterList(
    var results: List<Character>
) : Parcelable

@Parcelize
data class Character(
    var id: String,
    var name: String,
    var status: String,
    var species: String,
    var gender: String,
    var origin: LocationData,
    var location: LocationData,
    var image: String,
    var episode: List<String>,
) : Parcelable

@Parcelize

data class LocationData(
    var name: String,
) : Parcelable


