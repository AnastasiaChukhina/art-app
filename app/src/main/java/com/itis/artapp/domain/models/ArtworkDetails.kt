package com.itis.artapp.domain.models

data class ArtworkDetails(
    val id: Long,
    val imageId: String,
    val title: String,
    val description: String,
    val author: String,
    val placeOfOrigin: String?,
    val dateStart: Int?,
    val dateEnd: Int?,
    val dimensions: String,
    val display: String
)
