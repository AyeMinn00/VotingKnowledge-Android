package com.hexamass.votingknowledge.ui.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.hexamass.votingknowledge.datasource.DataSource
import com.hexamass.votingknowledge.ext.getResult
import com.hexamass.votingknowledge.model.Gallery
import com.hexamass.votingknowledge.model.Response
import com.hexamass.votingknowledge.ui.base.BaseViewModel
import com.hexamass.votingknowledge.model.Result
import kotlinx.coroutines.launch
import java.lang.Exception

class GalleryViewModel @ViewModelInject constructor(dataSource: DataSource) :
    BaseViewModel(dataSource) {

    val imageSets: MutableLiveData<Response<Gallery>> = MutableLiveData()

    init {
        getImageSets()
    }

    fun getImageSets() {
        launch {
            try {
                imageSets.value = Response.Loading("loading")
                when (val result = dataSource.getImageSets().getResult()) {
                    is Result.Success -> {
                        imageSets.value = Response.Done(result.data)
                    }
                    is Result.Error -> {
                        imageSets.value = Response.Error(result.exception)
                    }
                }
            } catch (e: Exception) {
                imageSets.value = Response.Error(Exception("network error"))
            }
        }
    }


}