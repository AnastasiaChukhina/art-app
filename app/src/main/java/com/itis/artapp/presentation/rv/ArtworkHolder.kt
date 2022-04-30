package com.itis.artapp.presentation.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.itis.artapp.databinding.ItemArtworkBinding
import com.itis.artapp.domain.converters.ArtworkDataConverter
import com.itis.artapp.domain.models.ArtworkSimple
import javax.inject.Inject

class ArtworkHolder(
    private val binding: ItemArtworkBinding,
    private val action: (Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    @Inject
    lateinit var converter: ArtworkDataConverter

    fun bind(item: ArtworkSimple) {
        with(binding) {
            ivImage.load(converter.createImageIconUrl(item.imageId))
            tvArtworkName.text = item.title
            itemView.setOnClickListener {
                action(item.id)
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            action: (Long) -> Unit
        ) = ArtworkHolder(
            ItemArtworkBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            action
        )
    }
}
