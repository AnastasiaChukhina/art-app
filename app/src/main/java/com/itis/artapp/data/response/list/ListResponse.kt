package com.itis.artapp.data.response.list

import com.google.gson.annotations.SerializedName
import com.itis.artapp.data.response.common.Config
import com.itis.artapp.data.response.common.Info

data class ListResponse(
    @SerializedName("config")
    val config: Config?,
    @SerializedName("data")
    val data: List<Data>?,
    @SerializedName("info")
    val info: Info?,
    @SerializedName("pagination")
    val pagination: Pagination?,
    @SerializedName("preference")
    val preference: Any?
)
