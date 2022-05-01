package com.itis.artapp.presentation.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.itis.artapp.databinding.ItemArtworkBinding
import com.itis.artapp.domain.converters.ArtworkDataConverter
import com.itis.artapp.domain.models.ArtworkSimple

class ArtworkHolder(
    private val binding: ItemArtworkBinding,
    private val converter: ArtworkDataConverter,
    private val action: (Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

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
            converter: ArtworkDataConverter,
            action: (Long) -> Unit
        ) = ArtworkHolder(
            ItemArtworkBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            converter,
            action
        )
    }
}
