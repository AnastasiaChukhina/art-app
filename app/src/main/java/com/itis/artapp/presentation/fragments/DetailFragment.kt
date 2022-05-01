package com.itis.artapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.itis.artapp.R
import com.itis.artapp.databinding.FragmentDetailBinding
import com.itis.artapp.domain.converters.ArtworkDataConverter
import com.itis.artapp.domain.models.ArtworkDetails
import com.itis.artapp.presentation.extensions.setBackButton
import com.itis.artapp.presentation.extensions.setTitle
import com.itis.artapp.presentation.extensions.showMessage
import com.itis.artapp.presentation.presenters.DetailPresenter
import com.itis.artapp.presentation.views.ArtworkDetailView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class DetailFragment : MvpAppCompatFragment(), ArtworkDetailView {

    @Inject
    @InjectPresenter
    lateinit var presenter: DetailPresenter

    @ProvidePresenter
    fun providePresenter(): DetailPresenter = presenter

    private val artworkId: Long by lazy {
        arguments?.getLong("ARG_ARTWORK_ID") ?: -1
    }

    @Inject
    lateinit var converter: ArtworkDataConverter

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)
        updateUI(artworkId)
    }

    override fun updateView(artwork: ArtworkDetails) {
        with(binding) {
            tvAuthor.text = artwork.author
            artwork.title.apply {
                tvArtworkName.text = this
                setActionBarTitle(this)
            }
            tvDateValue.text = converter.createDateString(artwork.dateStart, artwork.dateEnd)
            tvDimensionValue.text = artwork.dimensions
            tvDisplay.text = artwork.display
            ivArtwork.load(converter.createImageIconUrl(artwork.imageId))
        }
    }

    override fun showError(message: String) {
        showMessage(message)
    }

    private fun setActionBarTitle(title: String) {
        setTitle(title)
        setBackButton()
    }

    private fun updateUI(id: Long) {
        presenter.updateInfo(id)
    }
}
