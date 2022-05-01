package com.itis.artapp.presentation.presenters

import com.itis.artapp.di.modules.qualifiers.SchedulerUi
import com.itis.artapp.domain.usecases.GetArtworkDetailsUseCase
import com.itis.artapp.domain.usecases.GetArtworkListUseCase
import com.itis.artapp.presentation.views.ArtworkListView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import retrofit2.HttpException
import javax.inject.Inject

class ListPresenter @Inject constructor(
    private val getArtworkListUseCase: GetArtworkListUseCase,
    private val getArtworkDetailsUseCase: GetArtworkDetailsUseCase,
    @SchedulerUi private val scheduler: Scheduler
) : MvpPresenter<ArtworkListView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    override fun attachView(view: ArtworkListView?) {
        super.attachView(view)
    }

    override fun detachView(view: ArtworkListView?) {
        super.detachView(view)
    }

    fun getResultList(name: String) {
        disposables += getArtworkListUseCase(name)
            .observeOn(scheduler)
            .doOnSubscribe {
                viewState.showLoading()
            }
            .doAfterTerminate {
                viewState.hideLoading()
            }
            .subscribeBy(onSuccess = { list ->
                viewState.updateList(list)
            }, onError = { error ->
                when (error) {
                    HttpException::class -> viewState.showError("Unable to get artworks.")
                    else -> viewState.showError("Please, try again.")
                }
            })
    }

    fun getArtworkDetails(id: Long) {
        disposables += getArtworkDetailsUseCase(id)
            .observeOn(scheduler)
            .subscribeBy(onSuccess = {
                viewState.openDetailScreen(id)
            }, onError = { error ->
                when (error) {
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
