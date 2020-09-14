package com.hexamass.votingknowledge.ui.welcome

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hexamass.votingknowledge.R
import com.hexamass.votingknowledge.model.Response
import com.hexamass.votingknowledge.ui.language.LanguageAdapter
import com.hexamass.votingknowledge.ui.language.LanguageViewModel
import com.hexamass.votingknowledge.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_welcome.*

@AndroidEntryPoint
class WelcomeActivity : AppCompatActivity() {

    private var adapter: LanguageAdapter? = null
    private val viewModel: LanguageViewModel by viewModels()

    companion object{
        fun start(context : Context) = Intent(context, WelcomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        configView()
        watchData()
    }

    private fun configView() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        decoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        recyclerView.addItemDecoration(decoration)
        adapter = LanguageAdapter {
            onClickLanguage(it)
        }.apply {
            recyclerView.adapter = this
            recyclerView.layoutManager = LinearLayoutManager(this@WelcomeActivity)
        }
    }

    private fun onClickLanguage(position: Int) {
        val item = adapter?.getLanguage(position)
        viewModel.selectLanguage(item)
        startActivity(MainActivity.start(this))
    }

    private fun watchData() {
        viewModel.languages.observe(this, Observer {
            when (it) {
                is Response.Loading -> {
                    progressBar.visibility = toVisibility(true)
                    btnRetry.visibility = toVisibility(false)
                }
                is Response.Done -> {
                    progressBar.visibility = toVisibility(false)
                    btnRetry.visibility = toVisibility(false)
                    adapter?.submitList(it.data?.payload)
                }
                is Response.Error -> {
                    progressBar.visibility = toVisibility(false)
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