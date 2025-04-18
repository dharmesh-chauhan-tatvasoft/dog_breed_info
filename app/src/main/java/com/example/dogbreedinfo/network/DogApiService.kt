package com.example.dogbreedinfo.network

import com.example.dogbreedinfo.model.Breed
import com.example.dogbreedinfo.model.BreedDetails
import com.example.dogbreedinfo.ui.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiService {
    @GET(Constants.GET_BREEDS_API_END_POINT)
    suspend fun getBreeds(): List<Breed>

    @GET("${Constants.GET_BREEDS_IMAGE_API_END_POINT}{imageId}")
    suspend fun getBreedDetails(@Path("imageId") imageId: String): BreedDetails
}

object RetrofitInstance {
    val api: DogApiService by lazy {
        Retrofit.Builder().baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApiService::class.java)
    }
}