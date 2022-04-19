package com.itis.artapp.data.response.details

import com.google.gson.annotations.SerializedName
import com.itis.artapp.data.response.common.Config
import com.itis.artapp.data.response.common.Info

data class DetailsResponse(
    @SerializedName("config")
    val config: Config?,
    @SerializedName("data")
    val data: Data,
    @SerializedName("info")
    val info: Info?
)
