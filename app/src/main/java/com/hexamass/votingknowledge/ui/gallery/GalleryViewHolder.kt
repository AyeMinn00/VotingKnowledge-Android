package com.hexamass.votingknowledge.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hexamass.votingknowledge.R
import com.hexamass.votingknowledge.ext.mediumImageUrl
import com.hexamass.votingknowledge.ext.originalImageUrl
import com.hexamass.votingknowledge.ext.smallImageUrl
import com.hexamass.votingknowledge.model.ImageSet
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer

abstract class GalleryViewHolder(
    private val view: View,
    onClick: (ImageSet?) -> Unit
) : RecyclerView.ViewHolder(view), LayoutContainer {

    private var item: ImageSet? = null
    private var title: TextView? = null
    private var container: View? = null

    abstract fun bindImages(item: ImageSet)

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
            bindImages(it)
        }
    }

}

class GalleryViewHolder1(view: View, onClick: (ImageSet?) -> Unit) :
    GalleryViewHolder(view, onClick) {

    private var imgView: ImageView? = null

    init {
        imgView = view.findViewById(R.id.imgView)
    }

    override fun bindImages(item: ImageSet) {
        imgView?.setImageDrawable(null)
        if (item.images.isNotEmpty())
            Picasso.get().load(item.images[0]?.mediumImageUrl()).into(imgView)
    }

    companion object {
        fun create(parent: ViewGroup, onClick: (ImageSet?) -> Unit): GalleryViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
            return GalleryViewHolder1(view, onClick)
        }
    }
}

class GalleryViewHolder2(view: View, onClick: (ImageSet?) -> Unit) :
    GalleryViewHolder(view, onClick) {

    private var imgView: ImageView? = null
    private var imgView2: ImageView? = null

    init {
        imgView = view.findViewById(R.id.imgView1)
        imgView2 = view.findViewById(R.id.imgView2)
    }

    override fun bindImages(item: ImageSet) {
        imgView?.setImageDrawable(null)
        imgView2?.setImageDrawable(null)
        if (item.images.size >= 2) {
            Picasso.get().load(item.images[0]?.mediumImageUrl()).into(imgView)
            Picasso.get().load(item.images[1]?.mediumImageUrl()).into(imgView2)
        }
    }

    companion object {
        fun create(parent: ViewGroup, onClick: (ImageSet?) -> Unit): GalleryViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_2, parent, false)
            return GalleryViewHolder2(view, onClick)
        }
    }
}

open class GalleryViewHolder3(view: View, onClick: (ImageSet?) -> Unit) :
    GalleryViewHolder(view, onClick) {

    private var imgView: ImageView? = null
    private var imgView2: ImageView? = null
    private var imgView3: ImageView? = null

    init {
        imgView = view.findViewById(R.id.imgView1)
        imgView2 = view.findViewById(R.id.imgView2)
        imgView3 = view.findViewById(R.id.imgView3)
    }

    override fun bindImages(item: ImageSet) {
        imgView?.setImageDrawable(null)
        imgView2?.setImageDrawable(null)
        imgView3?.setImageDrawable(null)
        if (item.images.size >= 3) {
            Picasso.get().load(item.images[0]?.mediumImageUrl()).into(imgView)
            Picasso.get().load(item.images[1]?.mediumImageUrl()).into(imgView2)
            Picasso.get().load(item.images[2]?.mediumImageUrl()).into(imgView3)
        }
    }

    companion object {
        fun create(parent: ViewGroup, onClick: (ImageSet?) -> Unit): GalleryViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_3, parent, false)
            return GalleryViewHolder3(view, onClick)
        }
    }
}

class GalleryViewHolder4(view: View, onClick: (ImageSet?) -> Unit) :
    GalleryViewHolder3(view, onClick) {

    private var tv: TextView? = null

    init {
        tv = view.findViewById(R.id.labelImageLeftCount)
    }

    override fun bindImages(item: ImageSet) {
        super.bindImages(item)
        tv?.text = item.imageLeftCount
    }

    companion object {
        fun create(parent: ViewGroup, onClick: (ImageSet?) -> Unit): GalleryViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_4, parent, false)
            return GalleryViewHolder4(view, onClick)
        }
    }
}