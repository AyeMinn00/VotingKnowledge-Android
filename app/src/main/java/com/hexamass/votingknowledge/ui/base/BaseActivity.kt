package com.hexamass.votingknowledge.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    @get:LayoutRes
    abstract val layoutId: Int

    protected open fun watchData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initExtra()
        initUi()
        watchData()
        afterActivityCreated()
    }

    protected open fun initExtra() {
    }

    protected open fun initUi() {
    }

    protected open fun afterActivityCreated() {
    }

}