package com.hexamass.votingknowledge.ui.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hexamass.votingknowledge.R
import com.hexamass.votingknowledge.ext.originalImageUrl
import com.hexamass.votingknowledge.model.Content
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer

class ContentViewHolder(
    private val view: View,
    onClick: (Content?) -> Unit
) : RecyclerView.ViewHolder(view), LayoutContainer {

    private var item: Content? = null
    private var title: TextView? = null
    private var date: TextView? = null
    private var leftImageCount: TextView? = null
    private var imgView: ImageView? = null

    override val containerView: View?
        get() = view

    init {
        view.setOnClickListener {
            onClick(item)
        }
        title = view.findViewById(R.id.title)
        date = view.findViewById(R.id.tvTime)
        leftImageCount = view.findViewById(R.id.tvImgCount)
        imgView = view.findViewById(R.id.imgView)
    }

    fun bind(item: Content?) {
        this.item = item
        item?.let {
            title?.text = it.title
            date?.text = it.time
            leftImageCount?.text = it.leftImageCount
            if (it.images.isNotEmpty()) {
                Picasso.get().load(it.images[0].originalImageUrl())
                    .into(imgView)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, onClick: (Content?) -> Unit): ContentViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false)
            return ContentViewHolder(view, onClick)
        }
    }

}