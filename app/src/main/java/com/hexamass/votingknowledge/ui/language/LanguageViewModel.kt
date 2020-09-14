package com.hexamass.votingknowledge.ui.language

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.hexamass.votingknowledge.datasource.DataSource
import com.hexamass.votingknowledge.ext.getResult
import com.hexamass.votingknowledge.model.Language
import com.hexamass.votingknowledge.model.Languages
import com.hexamass.votingknowledge.model.Response
import com.hexamass.votingknowledge.model.Result
import com.hexamass.votingknowledge.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.lang.Exception

class LanguageViewModel @ViewModelInject constructor(dataSource: DataSource) :
    BaseViewModel(dataSource) {

    val languages: MutableLiveData<Response<Languages>> = MutableLiveData()

    init {
        getLanguages()
    }

    fun getLanguages() {
        launch {
            languages.value = Response.Loading("loading")
            try {
                when (val result = dataSource.getLanguages().getResult()) {
                    is Result.Success -> {
                        languages.value = Response.Done(result.data)
                    }
                    is Result.Error -> {
                        languages.value = Response.Error(result.exception)
                    }
                }
            } catch (e: Exception) {
                languages.value = Response.Error(e)
            }
        }
    }

    fun selectLanguage(language: Language?) {
        language?.let {
            dataSource.setLanguageId(it.id)
        }
    }

}