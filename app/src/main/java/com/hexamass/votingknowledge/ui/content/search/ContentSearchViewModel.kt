package com.hexamass.votingknowledge.ui.content.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hexamass.votingknowledge.datasource.DataSource
import com.hexamass.votingknowledge.model.NetworkState
import com.hexamass.votingknowledge.ui.base.BaseViewModel
import com.hexamass.votingknowledge.ui.content.ContentDataSourceFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ContentSearchViewModel @ViewModelInject
constructor(dataSource: DataSource) :
    BaseViewModel(dataSource) {

    private var debounceJob: Job? = null
    var query = ""

    private var sourceFactory = ContentDataSourceFactory(job, dataSource, "")
    private val config = PagedList.Config.Builder().setPageSize(20).setMaxSize(60).build()
    private var networkData = sourceFactory.dataSourceLiveData
    var responseList = LivePagedListBuilder(sourceFactory, config).build()
    var networkState: LiveData<NetworkState> = Transformations.switchMap(networkData) {
        it.networkStateLiveData
    }

    fun retry() {
        networkData.value?.retry?.invoke()
    }

    fun invalidateDataSet() {
        sourceFactory.dataSourceLiveData.value?.invalidate()
    }

    fun debounceSearch(q: String) {
        debounceJob?.cancel()
        debounceJob = launch {
            delay(500)
            performSearch(q)
        }
    }

    private fun performSearch(q: String) {
        sourceFactory.setQuery(q)
        invalidateDataSet()
    }

    override fun onCleared() {
        debounceJob?.cancel()
        super.onCleared()
    }

}