package com.itis.artapp.data.api.repositories

import com.itis.artapp.data.api.Api
import com.itis.artapp.data.response.details.DetailsResponse
import com.itis.artapp.data.response.list.ListResponse
import com.itis.artapp.domain.mappers.ModelMapper
import com.itis.artapp.domain.models.ArtworkDetails
import com.itis.artapp.domain.models.ArtworkSimple
import com.itis.artapp.domain.repositories.ArtworkRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ArtworkRepositoryImpl @Inject constructor(
    private val api: Api,
    private val detailsMapper: ModelMapper<DetailsResponse, ArtworkDetails>,
    private val listMapper: ModelMapper<ListResponse, MutableList<ArtworkSimple>>
) : ArtworkRepository {

    override fun getList(query: String): Single<MutableList<ArtworkSimple>> =
        api.getListByQuery(query)
            .map {
                listMapper.map(it)
            }

    override fun getDetails(id: Long): Single<ArtworkDetails> =
        api.getById(id)
            .map {
                detailsMapper.map(it)
            }
}
