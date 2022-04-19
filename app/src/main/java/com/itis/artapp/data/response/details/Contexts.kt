package com.itis.artapp.data.response.details

import com.google.gson.annotations.SerializedName

data class Contexts(
    @SerializedName("groupings")
    val groupings: List<String>?
)
