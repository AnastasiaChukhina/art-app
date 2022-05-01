package com.itis.artapp.presentation.views

import com.itis.artapp.domain.models.ArtworkDetails
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ArtworkDetailView: MvpView {

    fun updateView(artwork: ArtworkDetails)
    fun showError(message: String)
}
