package com.itis.artapp.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.itis.artapp.R
import com.itis.artapp.databinding.FragmentListBinding
import com.itis.artapp.domain.models.ArtworkSimple
import com.itis.artapp.presentation.extensions.hideBackButton
import com.itis.artapp.presentation.extensions.showMessage
import com.itis.artapp.presentation.presenters.ListPresenter
import com.itis.artapp.presentation.rv.ArtworkAdapter
import com.itis.artapp.presentation.rv.itemDecorstors.SpaceItemDecorator
import com.itis.artapp.presentation.views.ArtworkListView
import dagger.hilt.android.AndroidEntryPoint
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list), ArtworkListView {

    @Inject
    @InjectPresenter
    lateinit var presenter: ListPresenter

    @ProvidePresenter
    fun providePresenter(): ListPresenter = presenter

    private lateinit var binding: FragmentListBinding
    private lateinit var artworkAdapter: ArtworkAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)
        artworkAdapter = ArtworkAdapter { openDetails(it) }

        initSearchView()
        setActionBarAttrs()
        initAdapter()
    }

    override fun showLoading() {
        binding.progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progress.visibility = View.GONE
    }

    override fun updateList(list: MutableList<ArtworkSimple>) {
        if (list.size > 0) {
            artworkAdapter.submitList(list)
            showList()
        } else hideList()
    }

    private fun hideList() {
        with(binding) {
            rvArtworks.visibility = View.INVISIBLE
            tvArtworkNotFound.visibility = View.VISIBLE
        }
    }

    private fun showList() {
        with(binding) {
            rvArtworks.visibility = View.VISIBLE
            tvArtworkNotFound.visibility = View.INVISIBLE
        }
    }

    override fun showError(message: String) {
        showMessage(message)
    }

    override fun openDetailScreen(id: Long) {
        showCityFragment(id)
    }

    private fun openDetails(id: Long) {
        presenter.getArtworkDetails(id)
    }

    private fun setActionBarAttrs() {
        hideBackButton()
    }

    private fun initAdapter() {
        binding.rvArtworks.apply {
            adapter = artworkAdapter
            addItemDecoration(
                DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
            )
            addItemDecoration(SpaceItemDecorator(context))
        }
    }

    private fun initSearchView() {
        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getResultList(query?.trim().toString())
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }

    private fun showCityFragment(id: Long) {
        val bundle = Bundle().apply {
            putLong("ARG_ARTWORK_ID", id)
        }

        findNavController().navigate(
            R.id.action_listFragment_to_detailFragment,
            bundle
        )
    }
}
