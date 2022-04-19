package com.itis.artapp.di.modules

import com.itis.artapp.domain.converters.ArtworkDataConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ConverterModule {

    @Provides
    fun provideArtworkDataConverter() = ArtworkDataConverter()
}
