package com.hexamass.votingknowledge.ui.content

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hexamass.votingknowledge.datasource.DataSource
import com.hexamass.votingknowledge.ext.log
import com.hexamass.votingknowledge.model.NetworkState
import com.hexamass.votingknowledge.ui.base.BaseViewModel
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class ContentViewModel @ViewModelInject
constructor(dataSource: DataSource) :
    BaseViewModel(dataSource) {

    init {
        log("create ContentViewModel ...")
    }

    private var sourceFactory = ContentDataSourceFactory(job, dataSource)
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

}