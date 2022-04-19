package com.itis.artapp.di.modules

import com.itis.artapp.data.api.repositories.ArtworkRepositoryImpl
import com.itis.artapp.domain.repositories.ArtworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    fun provideArtworkRepository(
        impl: ArtworkRepositoryImpl
    ): ArtworkRepository
}
