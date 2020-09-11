package com.hexamass.votingknowledge.ui.content

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hexamass.votingknowledge.R
import com.hexamass.votingknowledge.model.Content
import com.hexamass.votingknowledge.model.NetworkState
import com.hexamass.votingknowledge.ui.viewholders.NetworkStateViewHolder
import java.lang.IllegalArgumentException

class ContentAdapter(
    private val onRetry: () -> Unit,
    private val onClick: (Content?) -> Unit
) : PagedListAdapter<Content, RecyclerView.ViewHolder>(userDiffCallback) {

    private var networkState: NetworkState? = null

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val newHasExtraRow = hasExtraRow()
        if (hadExtraRow != newHasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (newHasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_networkstate
        } else R.layout.item_content
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_content -> ContentViewHolder.create(parent, onClick)
            R.layout.item_networkstate -> NetworkStateViewHolder.create(parent, onRetry)
            else -> throw IllegalArgumentException("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_networkstate -> (holder as NetworkStateViewHolder).bind(networkState)
            R.layout.item_content -> (holder as ContentViewHolder).bind(getItem(position))
        }
    }

    companion object {
        val userDiffCallback = object : DiffUtil.ItemCallback<Content>() {
            override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
                return oldItem == newItem
            }
        }
    }

}