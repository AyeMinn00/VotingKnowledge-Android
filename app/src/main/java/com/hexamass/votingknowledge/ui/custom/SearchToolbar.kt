package com.hexamass.votingknowledge.ui.custom

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.hexamass.votingknowledge.R

class SearchToolbar : MyToolbar {

    private var clearButton: ImageView? = null
    private var searchEditText: EditText? = null
    private var queryListener: ((String) -> Unit)? = null

    constructor(context: Context) : super(context)

    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet)
            : super(context, attrs)

    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    override fun bindUI() {
        super.bindUI()
        searchEditText = findViewById(R.id.search_edit_text)
        searchEditText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isBlank()) {
                    clearButton?.visibility = View.INVISIBLE
                } else {
                    clearButton?.visibility = View.VISIBLE
                }
                queryListener?.invoke(s.toString())
            }
        })
        clearButton = findViewById(R.id.clear_button)
        clearButton?.setOnClickListener { onClickClearButton() }
    }

    fun setQueryListener(someF: (String) -> Unit) {
        queryListener = someF
    }

    fun setQuery(query: String) {
        searchEditText?.setText(query)
        searchEditText?.post {
            searchEditText?.setSelection(searchEditText?.text?.length ?: 0)
        }
    }

    private fun onClickClearButton() {
        searchEditText?.text = null
    }

    fun setFocus() {
        searchEditText?.requestFocus()
    }


}