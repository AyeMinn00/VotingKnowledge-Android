package com.hexamass.votingknowledge.ui.language

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hexamass.votingknowledge.R
import com.hexamass.votingknowledge.model.Language

class LanguageViewHolder(val view: View, onClick: (Int) -> Unit) :
    RecyclerView.ViewHolder(view) {

    private var language: TextView? = null
    private var check: ImageView? = null

    init {
        view.setOnClickListener { onClick(adapterPosition) }
        language = view.findViewById(R.id.language)
        check = view.findViewById(R.id.check)
    }

    fun bind(item: Language) {
        language?.text = item.lang
        check?.visibility = toVisibility(item.isSelected)
    }

    companion object {
        fun create(parent: ViewGroup, onClick: (Int) -> Unit): LanguageViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_language, parent, false)
            return LanguageViewHolder(view, onClick)
        }

        fun toVisibility(constraint: Boolean): Int {
            return if (constraint) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

}