package com.hexamass.votingknowledge.ui.contact

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hexamass.votingknowledge.R
import com.hexamass.votingknowledge.model.Contact
import com.hexamass.votingknowledge.model.Response
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_contact.*

@AndroidEntryPoint
class ContactActivity : AppCompatActivity() {

    private val viewModel: ContactViewModel by viewModels()
    private var adapter: ContactAdapter? = null

    companion object {
        fun start(context: Context) = Intent(context, ContactActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_navigate_up)
        }
        supportActionBar?.title = "Contact Information"
        configViews()
        watchData()
    }

    private fun configViews() {
        setUpRecyclerView()
        btnRetry.setOnClickListener {
            viewModel.getContacts()
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

    private fun setUpRecyclerView() {
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        decoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        recyclerView.addItemDecoration(decoration)
        adapter = ContactAdapter {
            onClickContact(it)
        }.apply {
            recyclerView.adapter = this
            recyclerView.layoutManager = LinearLayoutManager(this@ContactActivity)
        }
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.invalidateDataSet()
        }
    }

    private fun onClickContact(contact: Contact?) {

    }

    private fun watchData() {
        viewModel.contacts.observe(this, Observer {
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
                    swipeRefreshLayout.isRefreshing = false
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