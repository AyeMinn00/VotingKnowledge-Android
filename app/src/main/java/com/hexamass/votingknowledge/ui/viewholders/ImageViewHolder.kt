package com.hexamass.votingknowledge.ui.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hexamass.votingknowledge.R
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer

class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

    override val containerView: View?
        get() = view

    private var imgView: AppCompatImageView? = null

    init {
        imgView = view.findViewById(R.id.imgView)
    }

    fun onBind(url: String) {
        Picasso.get()
            .load(url)
            .resize(512, 512)
            .onlyScaleDown()
            .centerInside()
            .into(imgView)
    }

    companion object {
        fun create(viewGroup: ViewGroup): ImageViewHolder {
            val layout = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_image_view, viewGroup, false)
            return ImageViewHolder(layout)
        }
    }

}


class ImageViewAdapter : ListAdapter<String, ImageViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getLastIndex() = itemCount - 1

}