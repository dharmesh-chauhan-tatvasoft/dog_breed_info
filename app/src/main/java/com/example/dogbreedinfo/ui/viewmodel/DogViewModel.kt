package com.example.dogbreedinfo.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogbreedinfo.model.Breed
import com.example.dogbreedinfo.model.BreedDetails
import com.example.dogbreedinfo.network.RetrofitInstance
import kotlinx.coroutines.launch

class DogViewModel: ViewModel() {
    private val _breeds = MutableLiveData<List<Breed>>()

    private val _filteredBreeds = MutableLiveData<List<Breed>>()
    val filteredBreeds: LiveData<List<Breed>> = _filteredBreeds

    private val _breedDetails = MutableLiveData<BreedDetails>()
    val breedDetails: LiveData<BreedDetails> = _breedDetails

    init {
        fetchBreeds()
    }

    private fun fetchBreeds() {
        viewModelScope.launch {
            try {
                val breedList = RetrofitInstance.api.getBreeds()
                _breeds.value = breedList
                _filteredBreeds.value = breedList
            } catch (e: java.lang.Exception) {
                Log.e("DogViewModel", "Error: ${e.message}")
            }
        }
    }

    fun searchBreeds(query: String) {
        val allBreeds = _breeds.value ?: return
        _filteredBreeds.value = allBreeds.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun fetchBreedDetails(imageId: String) {
        viewModelScope.launch {
            try {
                val breedDetails = RetrofitInstance.api.getBreedDetails(imageId)
                _breedDetails.value = breedDetails
            } catch (e: java.lang.Exception) {
                Log.e("DogViewModel", "Error: ${e.message}")
            }
        }
    }
}