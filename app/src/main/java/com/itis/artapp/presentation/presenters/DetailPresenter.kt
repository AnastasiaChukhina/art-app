package com.itis.artapp.presentation.presenters

import com.itis.artapp.di.modules.qualifiers.SchedulerUi
import com.itis.artapp.domain.usecases.GetArtworkDetailsUseCase
import com.itis.artapp.presentation.views.ArtworkDetailView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import retrofit2.HttpException
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val getArtworkDetailsUseCase: GetArtworkDetailsUseCase,
    @SchedulerUi private val scheduler: Scheduler
) : MvpPresenter<ArtworkDetailView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    override fun attachView(view: ArtworkDetailView?) {
        super.attachView(view)
    }

    override fun detachView(view: ArtworkDetailView?) {
        super.detachView(view)
    }

    fun updateInfo(id: Long) {
        disposables += getArtworkDetailsUseCase(id)
            .observeOn(scheduler)
            .subscribeBy(onSuccess = {
                viewState.updateView(it)
            }, onError = {
                when (it) {
                    HttpException::class -> viewState.showError("Unable to get artwork info.")
                    else -> viewState.showError("Please, try again.")
                }
            })
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }
}
