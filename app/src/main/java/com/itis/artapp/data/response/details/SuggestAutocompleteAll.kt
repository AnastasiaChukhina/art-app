package com.itis.artapp.data.response.details

import com.google.gson.annotations.SerializedName

data class SuggestAutocompleteAll(
    @SerializedName("contexts")
    val contexts: Contexts?,
    @SerializedName("input")
    val input: List<String>?,
    @SerializedName("weight")
    val weight: Int?
)
