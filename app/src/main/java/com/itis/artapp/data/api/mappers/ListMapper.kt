package com.itis.artapp.data.api.mappers

import com.itis.artapp.data.response.list.ListResponse
import com.itis.artapp.domain.mappers.ModelMapper
import com.itis.artapp.domain.models.ArtworkSimple
import javax.inject.Inject

class ListMapper @Inject constructor(): ModelMapper<ListResponse, MutableList<ArtworkSimple>> {

    override fun map(source: ListResponse): MutableList<ArtworkSimple> {
        val list = mutableListOf<ArtworkSimple>()
        source.data?.forEach {
            list.add(
                ArtworkSimple(it.id, it.imageId, it.title)
            )
        }
        return list
    }
}
