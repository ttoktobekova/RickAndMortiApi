package com.example.rickandmortiapi.DI.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortiapi.DATA.repository.CharacterRepository

@Suppress("UNCHECKED_CAST")
class SharedViewModelFactory(private val repository: CharacterRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SharedViewModel(repository) as T
    }
}