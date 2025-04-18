package com.example.dogbreedinfo.model

data class Breed(
    val id: Int,
    val name: String,
    val description: String?,
    val temperament: String?,
    val life_span: String?,
    val reference_image_id: String,
    val height: Height?,
    val weight: Weight?,
    val image: Image?
)

data class Height(val metric: String?)
data class Image(val url: String?)
data class Weight(val imperial: String, val metric: String)

data class BreedDetails(
    val id: String,
    val url: String,
    val breeds: List<Breed>,
)
