package com.itis.artapp.domain.usecases

import com.itis.artapp.di.modules.qualifiers.SchedulerIo
import com.itis.artapp.domain.models.ArtworkSimple
import com.itis.artapp.domain.repositories.ArtworkRepository
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetArtworkListUseCase @Inject constructor(
    private val artworkRepository: ArtworkRepository,
    @SchedulerIo private val schedulerIo: Scheduler
) {

    operator fun invoke(
        query: String
    ): Single<MutableList<ArtworkSimple>> =
        artworkRepository.getList(query)
            .subscribeOn(schedulerIo)
}
