package com.example.rickandmortiapi.DI.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortiapi.DATA.repository.CharacterRepository
import com.example.rickandmortiapi.model.CharacterList
import com.example.rickandmortiapi.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: CharacterRepository) : ViewModel() {
    val listCharacterLD = MutableLiveData<Resource<CharacterList>>()
    val filterValue = MutableLiveData<Array<Int>>()
    val isFilter = MutableLiveData<Boolean>()

    init {
        filterValue.value = arrayOf(0, 0)
        isFilter.value = false
    }

    fun getCharacters(page: Int) {
        viewModelScope.launch {
            listCharacterLD.value = Resource.Loading()
            try {
                val response = repository.getCharacters(page)
                if (response.isSuccessful) {
                    listCharacterLD.value = Resource.Success(response.body()!!)
                    isFilter.value = false
                } else {
                    listCharacterLD.value = Resource.Error(response.message())
                }
            } catch (e: Exception) {
                listCharacterLD.value = Resource.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun getByStatusAndGender(status: String, gender: String, page: Int) {
        viewModelScope.launch {
            listCharacterLD.value = Resource.Loading()
            try {
                val response = repository.getCharactersByStatusAndGender(status, gender, page)
                if (response.isSuccessful) {
                    listCharacterLD.value = Resource.Success(response.body()!!)
                    isFilter.value = true
                } else {
                    listCharacterLD.value = Resource.Error(response.message())
                }
            } catch (e: Exception) {
                listCharacterLD.value = Resource.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun getCharactersByStatus(status: String, page: Int) {
        viewModelScope.launch {
            listCharacterLD.value = Resource.Loading()
            try {
                val response = repository.getCharactersByStatus(page, status)
                if (response.isSuccessful) {
                    listCharacterLD.value = Resource.Success(response.body()!!)
                    isFilter.value = true
                } else {
                    listCharacterLD.value = Resource.Error(response.message())
                }
            } catch (e: Exception) {
                listCharacterLD.value = Resource.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun getCharactersByGender(gender: String, page: Int) {
        viewModelScope.launch {
            listCharacterLD.value = Resource.Loading()
            try {
                val response = repository.getCharactersByGender(gender, page)
                if (response.isSuccessful) {
                    listCharacterLD.value = Resource.Success(response.body()!!)
                    isFilter.value = true
                } else {
                    listCharacterLD.value = Resource.Error(response.message())
                }
            } catch (e: Exception) {
                listCharacterLD.value = Resource.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun getByName(name: String) {
        viewModelScope.launch {
            listCharacterLD.value = Resource.Loading()
            try {
                val response = repository.getCharactersByName(name)
                if (response.isSuccessful) {
                    listCharacterLD.value = Resource.Success(response.body()!!)
                    isFilter.value = true
                } else {
                    listCharacterLD.value = Resource.Error(response.message())
                }
            } catch (e: Exception) {
                listCharacterLD.value = Resource.Error(e.message ?: "Unknown Error")
            }
        }
    }
}
