package com.hexamass.votingknowledge.datasource.pref

import android.app.Application
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import javax.inject.Inject

interface IPref {
    fun setLanguageId(id: String)
    fun getLanguageId(): String
}

class Pref @Inject constructor(context: Application) : IPref {

    companion object {
        const val PREF_LANG = "PREF_LANG"
    }

    private val pref = PreferenceManager.getDefaultSharedPreferences(context)

    override fun setLanguageId(id: String) {
        pref.edit { putString(PREF_LANG, id) }
    }

    override fun getLanguageId(): String {
        return pref.getString(PREF_LANG, "5f1939a4c329f627d5d3d398") ?: "5f1939a4c329f627d5d3d398"
    }
}