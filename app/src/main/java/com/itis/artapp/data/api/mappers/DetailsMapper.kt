package com.itis.artapp.data.api.mappers

import com.itis.artapp.data.response.details.DetailsResponse
import com.itis.artapp.domain.mappers.ModelMapper
import com.itis.artapp.domain.models.ArtworkDetails
import javax.inject.Inject

class DetailsMapper @Inject constructor() : ModelMapper<DetailsResponse, ArtworkDetails> {
    override fun map(source: DetailsResponse): ArtworkDetails {
        val data = source.data
        return ArtworkDetails(
            data.id,
            data.imageId,
            data.dateQualifierTitle,
            data.thumbnail.altText,
            data.artistTitle,
            data.placeOfOrigin,
            data.dateStart,
            data.dateEnd,
            data.dimensions,
            data.mediumDisplay
        )
    }
}
