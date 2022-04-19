package com.itis.artapp.di.modules

import com.itis.artapp.data.api.mappers.DetailsMapper
import com.itis.artapp.data.api.mappers.ListMapper
import com.itis.artapp.data.response.details.DetailsResponse
import com.itis.artapp.data.response.list.ListResponse
import com.itis.artapp.domain.mappers.ModelMapper
import com.itis.artapp.domain.models.ArtworkDetails
import com.itis.artapp.domain.models.ArtworkSimple
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {

    @Binds
    fun bindsDetailsMapper(
        impl: DetailsMapper
    ): ModelMapper<DetailsResponse, ArtworkDetails>

    @Binds
    fun bindsListMapper(
        impl: ListMapper
    ): ModelMapper<ListResponse, List<ArtworkSimple>>
}
