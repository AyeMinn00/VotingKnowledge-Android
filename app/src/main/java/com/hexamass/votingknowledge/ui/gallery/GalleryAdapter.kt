package com.hexamass.votingknowledge.ui.gallery

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hexamass.votingknowledge.model.ImageSet

class GalleryAdapter(private val onClick: (ImageSet?) -> Unit) :
    ListAdapter<ImageSet, GalleryViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder.create(parent, onClick)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ImageSet>() {
            override fun areItemsTheSame(oldItem: ImageSet, newItem: ImageSet): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImageSet, newItem: ImageSet): Boolean {
                return oldItem == newItem
            }
        }
    }
}