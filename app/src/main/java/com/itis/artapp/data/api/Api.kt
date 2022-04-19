package com.itis.artapp.data.api

import com.itis.artapp.data.response.details.DetailsResponse
import com.itis.artapp.data.response.list.ListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("search?fields=id,title,image_id")
    fun getListByQuery(@Query("q") city: String): Single<ListResponse>

    @GET("{id}")
    fun getById(@Path("id") id: Long): Single<DetailsResponse>
}
