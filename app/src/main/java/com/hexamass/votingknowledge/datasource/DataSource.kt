package com.hexamass.votingknowledge.datasource

import com.hexamass.votingknowledge.datasource.pref.IPref
import com.hexamass.votingknowledge.datasource.remote.ApiService

class DataSource(private val apiService: ApiService, private val pref: IPref) {

    fun getContents(count: Int, page: Int) =
        apiService.getContentListAsync(count, page, pref.getLanguageId())

    fun getContentsByKey(count: Int, page: Int, key: String) =
        apiService.getContentListByKeyAsync(count, page, key, pref.getLanguageId())

}