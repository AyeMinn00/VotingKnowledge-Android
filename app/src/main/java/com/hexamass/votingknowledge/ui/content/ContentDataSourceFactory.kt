package com.hexamass.votingknowledge.ui.content

import androidx.lifecycle.MutableLiveData
import com.hexamass.votingknowledge.datasource.DataSource
import com.hexamass.votingknowledge.model.Content
import kotlinx.coroutines.Job

class ContentDataSourceFactory(
    private val job: Job,
    private val dataSource: DataSource, private var searchKey: String? = null
) : androidx.paging.DataSource.Factory<Int, Content>() {

    val dataSourceLiveData = MutableLiveData<ContentDataSource>()

    fun setQuery(query: String) {
        this.searchKey = query
    }

    override fun create(): androidx.paging.DataSource<Int, Content> {
        val dataSource = ContentDataSource(job, dataSource, searchKey)
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }

}