package com.hexamass.votingknowledge.datasource

import com.hexamass.votingknowledge.datasource.pref.IPref
import com.hexamass.votingknowledge.datasource.remote.ApiService

class DataSource(private val apiService: ApiService, private val pref: IPref) : IPref by pref,
    ApiService by apiService