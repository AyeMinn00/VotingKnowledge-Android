package com.hexamass.votingknowledge.ui.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hexamass.votingknowledge.R
import com.hexamass.votingknowledge.ext.log
import com.hexamass.votingknowledge.model.Content
import com.hexamass.votingknowledge.model.NetworkState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_content.*

@AndroidEntryPoint
class ContentFragment : Fragment() {

    private val viewModel: ContentViewModel by viewModels()
    private var adapter: ContentAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("onViewCreated()")
        configSwipeRefreshLayout()
        configRecyclerView()
        watchData()
    }

    override fun onResume() {
        super.onResume()
        log("onResume()")
    }

    private fun configSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.invalidateDataSet()
        }
    }

    private fun configRecyclerView() {
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        decoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)
        recyclerView.addItemDecoration(decoration)
        adapter = ContentAdapter(
            onRetry = { onClickRetry() },
            onClick = { onClickContent(it) })
            .apply {
                recyclerView.adapter = this
                recyclerView.layoutManager = LinearLayoutManager(activity)
            }
    }

    private fun onClickRetry() {
        viewModel.retry()
    }

    private fun onClickContent(content: Content?) {

    }

    private fun watchData() {
        viewModel.responseList.observe(viewLifecycleOwner, Observer {
            adapter?.submitList(it)
            swipeRefreshLayout.isRefreshing = false
        })

        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            onNetworkStateChange(it)
        })
    }

    private fun onNetworkStateChange(state: NetworkState) {
        adapter?.setNetworkState(state)
    }

}