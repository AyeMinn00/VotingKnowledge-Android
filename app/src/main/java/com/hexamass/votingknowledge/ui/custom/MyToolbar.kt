package com.hexamass.votingknowledge.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.hexamass.votingknowledge.R
import com.hexamass.votingknowledge.ext.log

open class MyToolbar : Toolbar {

    //    private var titleTextView: TextView? = null
    private var backButton: ImageView? = null

    constructor(context: Context) : super(context) {
        initUi(context)
    }

    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {
        initUi(context)
    }

    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initUi(context)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        try {
            bindUI()
        } catch (e: ClassNotFoundException) {
            log(e.message ?: "Unknown error")
        }
    }

    private fun initUi(context: Context) {
    }

    protected open fun bindUI() {
//        titleTextView = findViewById(R.id.titleTextView)
        backButton = findViewById(R.id.back_button)
        backButton?.setOnClickListener { onClickBackButton() }
    }

    private fun onClickBackButton() {
        val activity = context as AppCompatActivity
        activity.onBackPressed()
    }

    fun setTitle(title: String) {
//        titleTextView?.text = title
    }


}