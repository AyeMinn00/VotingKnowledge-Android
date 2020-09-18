package com.hexamass.votingknowledge.ui.content

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.hexamass.votingknowledge.datasource.DataSource
import com.hexamass.votingknowledge.ext.getResult
import com.hexamass.votingknowledge.ext.log
import com.hexamass.votingknowledge.model.Content
import com.hexamass.votingknowledge.model.NetworkState
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import com.hexamass.votingknowledge.model.Result
import com.hexamass.votingknowledge.model.formatContent


class ContentDataSource(
    private val job: Job,
    private val dataSource: DataSource,
    private val searchQuery: String? = null
) : PageKeyedDataSource<Int, Content>(), CoroutineScope {

    private var PAGE = 0
    private val count = 10

    var retry: (() -> Unit)? = null

    val networkStateLiveData = MutableLiveData<NetworkState>()

    override val coroutineContext: CoroutineContext get() = job + Dispatchers.IO

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Content>
    ) {
        networkStateLiveData.postValue(NetworkState.LOADING)
        launch {
            try {
                val result = if (searchQuery == null)
                    dataSource.getContents(count, PAGE).getResult()
                else dataSource.getContentsByKey(count, PAGE, searchQuery).getResult()
                when (result) {
                    is Result.Success -> {
                        retry = null
                        networkStateLiveData.postValue(NetworkState.LOADED)
                        result.data?.let {
                            callback.onResult(formatContent(it.payload.contents), null, PAGE + 1)
                            if (it.payload.contents.isNotEmpty())
                                networkStateLiveData.postValue(NetworkState.LOADED)
                        }
                    }
                    is Result.Error -> {
                        retry = { loadInitial(params, callback) }
                        val error = NetworkState.error(result.exception.message ?: "unknown error")
                        networkStateLiveData.postValue(error)
                    }
                }
            } catch (e: Exception) {
                retry = { loadInitial(params, callback) }
                val error = NetworkState.error(e.message ?: "unknown error")
                networkStateLiveData.postValue(error)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Content>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Content>) {
        networkStateLiveData.postValue(NetworkState.LOADING)
        launch {
            try {
                val result = if (searchQuery == null)
                    dataSource.getContents(count, params.key).getResult()
                else dataSource.getContentsByKey(count, params.key, searchQuery).getResult()
                when (result) {
                    is Result.Success -> {
                        retry = null
                        networkStateLiveData.postValue(NetworkState.LOADED)
                        result.data?.let {
                            callback.onResult(formatContent(it.payload.contents), params.key + 1)
                            if (it.payload.contents.isNotEmpty())
                                networkStateLiveData.postValue(NetworkState.LOADED)
                        }
                    }
                    is Result.Error -> {
                        retry = { loadAfter(params, callback) }
                        val error = NetworkState.error(result.exception.message ?: "unknown error")
                        networkStateLiveData.postValue(error)
                    }
                }
            } catch (e: Exception) {
                retry = { loadAfter(params, callback) }
                val error = NetworkState.error(e.message ?: "unknown error")
                networkStateLiveData.postValue(error)
            }
        }
    }
}