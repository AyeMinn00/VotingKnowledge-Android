package com.hexamass.votingknowledge.ui.language

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hexamass.votingknowledge.model.Language

class LanguageAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<Language, LanguageViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        return LanguageViewHolder.create(parent, onClick)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun getLanguage(position: Int) = getItem(position)

    fun refreshDataSet(position: Int) {
        val item = getItem(position)
        currentList.forEach {
            it.isSelected = it.id == item.id
        }
        notifyDataSetChanged()
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Language>() {
            override fun areItemsTheSame(oldItem: Language, newItem: Language): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Language, newItem: Language): Boolean {
                return oldItem == newItem
            }
        }
    }

}