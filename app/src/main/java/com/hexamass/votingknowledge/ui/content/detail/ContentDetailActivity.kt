package com.hexamass.votingknowledge.ui.content.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.hexamass.votingknowledge.R
import com.hexamass.votingknowledge.model.ParcelContent
import com.hexamass.votingknowledge.ui.custom.TabLayoutMediator
import com.hexamass.votingknowledge.ui.viewholders.ImageViewAdapter
import kotlinx.android.synthetic.main.activity_content_detail.*

class ContentDetailActivity : AppCompatActivity() {

    companion object {

        const val TAG_CONTENT = "TAG_CONTENT"

        fun start(context: Context, parcel: ParcelContent): Intent {
            val intent = Intent(context, ContentDetailActivity::class.java)
            intent.putExtra(TAG_CONTENT, parcel)
            return intent
        }
    }

    private var imageViewAdapter: ImageViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_detail)
        configViews()
        if (intent.hasExtra(TAG_CONTENT)){
            val value = intent.getParcelableExtra<ParcelContent>(TAG_CONTENT)
            value?.let {
                imageViewAdapter?.submitList(it.images)
                tvTitle.text = it.title
                body.text = it.body
                tvDate.text = it.time
            }

        }
    }

    private fun configViews() {
        imageViewAdapter = ImageViewAdapter().apply {
            viewpager.adapter = this
            TabLayoutMediator(
                tabLayout,
                viewpager,
                object : TabLayoutMediator.OnConfigureTabCallback {
                    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                        tab.text = ""
                    }
                }).attach()
        }
    }

}