package com.hexamass.votingknowledge.ui.contact

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hexamass.votingknowledge.model.Contact

class ContactAdapter(val onClick : (Contact?) -> Unit)
    : ListAdapter<Contact, ContactViewHolder>(diffUitl){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val diffUitl = object : DiffUtil.ItemCallback<Contact>(){
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem == newItem
            }
        }
    }

}