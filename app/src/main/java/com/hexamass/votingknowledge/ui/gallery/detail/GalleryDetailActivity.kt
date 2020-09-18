package com.hexamass.votingknowledge.ui.gallery.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hexamass.votingknowledge.R
import com.hexamass.votingknowledge.model.ParcelImages
import com.hexamass.votingknowledge.ui.viewholders.ImageViewAdapter
import kotlinx.android.synthetic.main.activity_gallery_detail.*
import kotlinx.android.synthetic.main.activity_gallery_detail.toolbar

class GalleryDetailActivity : AppCompatActivity() {

    private var adapter: ImageViewAdapter? = null

    companion object {
        private const val TAG_IMAGES = "TAG_IMAGES"
        fun start(context: Context, parcel: ParcelImages): Intent {
            val intent = Intent(context, GalleryDetailActivity::class.java)
            intent.putExtra(TAG_IMAGES, parcel)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_navigate_up)
        }
        supportActionBar?.title = ""
        configViews()
        if (intent.hasExtra(TAG_IMAGES)) {
            val value = intent.getParcelableExtra<ParcelImages>(TAG_IMAGES)
            value?.let {
                adapter?.submitList(it.images)
            }

        }
    }

    private fun configViews() {
        val decoration = DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        decoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        recyclerView.addItemDecoration(decoration)
        adapter = ImageViewAdapter().apply {
            recyclerView.adapter = this
            recyclerView.layoutManager = LinearLayoutManager(this@GalleryDetailActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}