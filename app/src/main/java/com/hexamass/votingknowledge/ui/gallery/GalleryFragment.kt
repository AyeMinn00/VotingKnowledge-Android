package com.hexamass.votingknowledge.ui.gallery

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
import com.hexamass.votingknowledge.model.ImageSet
import com.hexamass.votingknowledge.model.Response
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_content.*
import kotlinx.android.synthetic.main.fragment_image.*
import kotlinx.android.synthetic.main.fragment_image.recyclerView
import kotlinx.android.synthetic.main.fragment_image.swipeRefreshLayout

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var adapter: GalleryAdapter? = null
    private val viewModel: GalleryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        watchData()
        btnRetry.setOnClickListener {
            viewModel.getImageSets()
        }
    }

    private fun setUpRecyclerView() {
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        decoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)
        recyclerView.addItemDecoration(decoration)
        adapter = GalleryAdapter {
            onClickImageSet(it)
        }.apply {
            recyclerView.adapter = this
            recyclerView.layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun onClickImageSet(item: ImageSet?) {

    }

    private fun watchData() {
        viewModel.imageSets.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Response.Loading -> {
                    progressBar.visibility = toVisibility(true)
                    swipeRefreshLayout.visibility = toVisibility(false)
                    btnRetry.visibility = toVisibility(false)
                }
                is Response.Done -> {
                    progressBar.visibility = toVisibility(false)
                    swipeRefreshLayout.visibility = toVisibility(true)
                    btnRetry.visibility = toVisibility(false)
                    adapter?.submitList(it.data?.payload)
                }
                is Response.Error -> {
                    progressBar.visibility = toVisibility(false)
                    swipeRefreshLayout.visibility = toVisibility(false)
                    btnRetry.visibility = toVisibility(true)
                }
            }
        })
    }

    private fun toVisibility(constraint: Boolean): Int {
        return if (constraint) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

}