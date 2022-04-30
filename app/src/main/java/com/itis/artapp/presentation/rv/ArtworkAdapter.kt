package com.itis.artapp.presentation.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.itis.artapp.domain.models.ArtworkSimple

class ArtworkAdapter(
    private val action: (Long) -> Unit
) : ListAdapter<ArtworkSimple, ArtworkHolder>(ArtworkDiffUtilsCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkHolder =
        ArtworkHolder.create(parent, action)

    override fun onBindViewHolder(holder: ArtworkHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<ArtworkSimple>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list)
        )
    }
}

class ArtworkDiffUtilsCallback : DiffUtil.ItemCallback<ArtworkSimple>() {

    override fun areItemsTheSame(
        oldItem: ArtworkSimple,
        newItem: ArtworkSimple
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: ArtworkSimple,
        newItem: ArtworkSimple
    ): Boolean = oldItem == newItem
}
