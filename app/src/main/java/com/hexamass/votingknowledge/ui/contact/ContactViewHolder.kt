package com.hexamass.votingknowledge.ui.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hexamass.votingknowledge.R
import com.hexamass.votingknowledge.model.Contact
import kotlinx.android.extensions.LayoutContainer

class ContactViewHolder(val view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

    private var title: TextView? = null
    private var description: TextView? = null

    override val containerView: View?
        get() = view

    init {
        title = view.findViewById(R.id.title)
        description = view.findViewById(R.id.description)
    }

    fun bind(item: Contact) {
        title?.text = item.title
        description?.text = item.description
    }

    companion object {
        fun create(parent: ViewGroup): ContactViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
            return ContactViewHolder(view)
        }
    }


}