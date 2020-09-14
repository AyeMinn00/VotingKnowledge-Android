package com.hexamass.votingknowledge.ui.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.hexamass.votingknowledge.R
import com.hexamass.votingknowledge.ext.log
import com.hexamass.votingknowledge.model.NetworkState
import com.hexamass.votingknowledge.model.Status
import kotlinx.android.extensions.LayoutContainer

class NetworkStateViewHolder(
    private val view: View,
    onViewClick: () -> Unit
) : RecyclerView.ViewHolder(view), LayoutContainer {

    private var btnRetry: MaterialButton? = null
    private var progressBar: ProgressBar? = null

    init {
        btnRetry = view.findViewById(R.id.btnRetry)
        btnRetry?.setOnClickListener { onViewClick.invoke() }
        progressBar = view.findViewById(R.id.progressBar)
    }

    override val containerView: View? get() = view

    fun bind(state: NetworkState?) {
        progressBar?.visibility = toVisibility(state?.status == Status.RUNNING)
        btnRetry?.visibility = toVisibility(state?.status == Status.FAILED)
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_networkstate, parent, false)
            return NetworkStateViewHolder(view, retryCallback)
        }

        fun toVisibility(constraint: Boolean): Int {
            return if (constraint) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }
    }

}