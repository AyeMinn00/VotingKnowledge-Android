package com.hexamass.votingknowledge.datasource

import com.hexamass.votingknowledge.datasource.pref.IPref
import com.hexamass.votingknowledge.datasource.remote.ApiService

class DataSource(private val apiService: ApiService, private val pref: IPref) :  IPref by pref {

    fun getContents(count: Int, page: Int) =
        apiService.getContentListAsync(count, page, pref.getLanguageId())

    fun getContentsByKey(count: Int, page: Int, key: String) =
        apiService.getContentListByKeyAsync(count, page, key, pref.getLanguageId())

    fun getImageSets()  = apiService.getImageSets(pref.getLanguageId())

    fun getLanguages() = apiService.getLanguages()
    fun getContacts() = apiService.getContacts()
}