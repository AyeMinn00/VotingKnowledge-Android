package com.hexamass.votingknowledge.ui.content.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hexamass.votingknowledge.R
import com.hexamass.votingknowledge.model.Content
import com.hexamass.votingknowledge.model.NetworkState
import com.hexamass.votingknowledge.ui.content.ContentAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_content.*
import kotlinx.android.synthetic.main.toolbar_search.*

@AndroidEntryPoint
class ContentSearchActivity : AppCompatActivity() {

    private val viewModel: ContentSearchViewModel by viewModels()
    private var adapter: ContentAdapter? = null

    companion object {
        fun start(context: Context) = Intent(context, ContentSearchActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_search)
        configViews()
    }

    private fun configViews() {
        toolbar.setFocus()
        toolbar.setQueryListener {
            search(it)
        }
        configSwipeRefreshLayout()
        configRecyclerView()
        watchData()
    }

    private fun configSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.invalidateDataSet()
        }
    }

    private fun configRecyclerView() {
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        decoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        recyclerView.addItemDecoration(decoration)
        adapter = ContentAdapter(
            onRetry = { onClickRetry() },
            onClick = { onClickContent(it) })
            .apply {
                recyclerView.adapter = this
                recyclerView.layoutManager = LinearLayoutManager(this@ContentSearchActivity)
            }
    }

    private fun search(q: String) {
        if (viewModel.query != q) {
            viewModel.debounceSearch(q)
            viewModel.query = q
        }
    }

    private fun onClickRetry() {
        viewModel.retry()
    }

    private fun onClickContent(content: Content?) {

    }

    private fun watchData() {
        viewModel.responseList.observe(this, Observer {
            adapter?.submitList(it)
            swipeRefreshLayout.isRefreshing = false
        })

        viewModel.networkState.observe(this, Observer {
            onNetworkStateChange(it)
        })
    }

    private fun onNetworkStateChange(state: NetworkState) {
        adapter?.setNetworkState(state)
    }

}