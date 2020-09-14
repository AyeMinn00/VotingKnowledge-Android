package com.hexamass.votingknowledge.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hexamass.votingknowledge.R
import com.hexamass.votingknowledge.model.ImageSet
import kotlinx.android.extensions.LayoutContainer

class GalleryViewHolder(
    private val view: View,
    onClick: (ImageSet?) -> Unit
) : RecyclerView.ViewHolder(view), LayoutContainer {

    private var item: ImageSet? = null
    private var title: TextView? = null
    private var container: View? = null

    override val containerView: View?
        get() = view

    init {
        view.setOnClickListener {
            onClick(item)
        }
        title = view.findViewById(R.id.title)
        container = view.findViewById(R.id.containerView)
    }

    fun bind(item: ImageSet?) {
        this.item = item
        item?.let {
            title?.text = it.title
        }
    }

    companion object {
        fun create(parent: ViewGroup, onClick: (ImageSet?) -> Unit): GalleryViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
            return GalleryViewHolder(view, onClick)
        }
    }

}