package com.itis.artapp.presentation.views

import com.itis.artapp.domain.models.ArtworkSimple
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface ArtworkListView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun updateList(list: MutableList<ArtworkSimple>)

    @Skip
    fun showError(message: String)

    @OneExecution
    fun openDetailScreen(id: Long)
}
