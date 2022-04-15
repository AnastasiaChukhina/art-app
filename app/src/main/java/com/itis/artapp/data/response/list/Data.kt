package com.itis.artapp.data.response.list

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: Long,
    @SerializedName("image_id")
    val imageId: String,
    @SerializedName("_score")
    val score: Double,
    @SerializedName("title")
    val title: String
)
