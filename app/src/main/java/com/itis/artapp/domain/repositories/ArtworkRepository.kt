package com.itis.artapp.domain.repositories

import com.itis.artapp.domain.models.ArtworkDetails
import com.itis.artapp.domain.models.ArtworkSimple
import io.reactivex.rxjava3.core.Single

interface ArtworkRepository {
    fun getList(query: String): Single<MutableList<ArtworkSimple>>
    fun getDetails(id: Long): Single<ArtworkDetails>
}
